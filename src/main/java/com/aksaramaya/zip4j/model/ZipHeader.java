package com.aksaramaya.zip4j.model;

import com.aksaramaya.zip4j.headers.HeaderSignature;

public abstract class ZipHeader {

  private HeaderSignature signature;

  public HeaderSignature getSignature() {
    return signature;
  }

  public void setSignature(HeaderSignature signature) {
    this.signature = signature;
  }
}
