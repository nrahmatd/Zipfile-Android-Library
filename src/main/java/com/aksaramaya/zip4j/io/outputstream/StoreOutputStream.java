package com.aksaramaya.zip4j.io.outputstream;

class StoreOutputStream extends CompressedOutputStream {

  public StoreOutputStream(CipherOutputStream cipherOutputStream) {
    super(cipherOutputStream);
  }

}
