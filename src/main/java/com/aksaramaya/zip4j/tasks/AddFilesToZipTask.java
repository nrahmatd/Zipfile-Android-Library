package com.aksaramaya.zip4j.tasks;

import com.aksaramaya.zip4j.exception.ZipException;
import com.aksaramaya.zip4j.headers.HeaderWriter;
import com.aksaramaya.zip4j.model.ZipModel;
import com.aksaramaya.zip4j.model.ZipParameters;
import com.aksaramaya.zip4j.progress.ProgressMonitor;
import com.aksaramaya.zip4j.tasks.AddFilesToZipTask.AddFilesToZipTaskParameters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class AddFilesToZipTask extends AbstractAddFileToZipTask<AddFilesToZipTaskParameters> {

  public AddFilesToZipTask(ZipModel zipModel, char[] password, HeaderWriter headerWriter, AsyncTaskParameters asyncTaskParameters) {
    super(zipModel, password, headerWriter, asyncTaskParameters);
  }

  @Override
  protected void executeTask(AddFilesToZipTaskParameters taskParameters, ProgressMonitor progressMonitor)
      throws IOException {

    verifyZipParameters(taskParameters.zipParameters);
    addFilesToZip(taskParameters.filesToAdd, progressMonitor, taskParameters.zipParameters, taskParameters.charset);
  }

  @Override
  protected long calculateTotalWork(AddFilesToZipTaskParameters taskParameters) throws ZipException {
    return calculateWorkForFiles(taskParameters.filesToAdd, taskParameters.zipParameters);
  }

  @Override
  protected ProgressMonitor.Task getTask() {
    return super.getTask();
  }

  public static class AddFilesToZipTaskParameters extends AbstractZipTaskParameters {
    private List<File> filesToAdd;
    private ZipParameters zipParameters;

    public AddFilesToZipTaskParameters(List<File> filesToAdd, ZipParameters zipParameters, Charset charset) {
      super(charset);
      this.filesToAdd = filesToAdd;
      this.zipParameters = zipParameters;
    }
  }
}
