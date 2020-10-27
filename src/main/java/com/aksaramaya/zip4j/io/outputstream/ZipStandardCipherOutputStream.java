package com.aksaramaya.zip4j.io.outputstream;

import com.aksaramaya.zip4j.crypto.StandardEncrypter;
import com.aksaramaya.zip4j.exception.ZipException;
import com.aksaramaya.zip4j.model.ZipParameters;
import com.aksaramaya.zip4j.util.Zip4jUtil;

import java.io.IOException;
import java.io.OutputStream;

class ZipStandardCipherOutputStream extends CipherOutputStream<StandardEncrypter> {

  public ZipStandardCipherOutputStream(ZipEntryOutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException, ZipException {
    super(outputStream, zipParameters, password);
  }

  @Override
  protected StandardEncrypter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException, ZipException {
    long key = getEncryptionKey(zipParameters);
    StandardEncrypter encrypter = new StandardEncrypter(password, key);
    writeHeaders(encrypter.getHeaderBytes());
    return encrypter;
  }

  @Override
  public void write(int b) throws IOException {
    write(new byte[] {(byte) b});
  }

  @Override
  public void write(byte[] b) throws IOException {
    write(b, 0, b.length);
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    super.write(b, off, len);
  }

  private long getEncryptionKey(ZipParameters zipParameters) {
    if (zipParameters.isWriteExtendedLocalFileHeader()) {
      long dosTime = Zip4jUtil.epochToExtendedDosTime(zipParameters.getLastModifiedFileTime());
      return (dosTime & 0x0000ffff) << 16;
    }

    return zipParameters.getEntryCRC();
  }
}
