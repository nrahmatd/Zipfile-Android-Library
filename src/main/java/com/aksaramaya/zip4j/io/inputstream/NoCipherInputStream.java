package com.aksaramaya.zip4j.io.inputstream;

import com.aksaramaya.zip4j.crypto.Decrypter;
import com.aksaramaya.zip4j.exception.ZipException;
import com.aksaramaya.zip4j.model.LocalFileHeader;

import java.io.IOException;

class NoCipherInputStream extends CipherInputStream {

  public NoCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] password) throws IOException, ZipException {
    super(zipEntryInputStream, localFileHeader, password);
  }

  @Override
  protected Decrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] password) {
    return new NoDecrypter();
  }

  static class NoDecrypter implements Decrypter {

    @Override
    public int decryptData(byte[] buff, int start, int len) {
      return len;
    }
  }
}
