package com.aksaramaya.zip4j.tasks;

import java.nio.charset.Charset;

public abstract class AbstractZipTaskParameters {

  protected Charset charset;

  protected AbstractZipTaskParameters(Charset charset) {
    this.charset = charset;
  }
}
