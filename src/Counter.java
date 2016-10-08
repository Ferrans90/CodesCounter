import java.io.*;

public class Counter {
  private int lineNumber;
  private File sourceCodePath;

  public Counter(String path) {
    sourceCodePath = new File(path);
    lineNumber = 0;
  }

  public void printSourceCodeLineNumber() {
    recurFilePath(sourceCodePath);
    System.out.println("This directory's " + sourceCodePath.toPath().toString()
        + " source code linenumber is " + lineNumber);
  }


  private void recurFilePath(File dir) {
    for (File f : dir.listFiles()) {
      if (f.isDirectory()) {
        recurFilePath(f);
      } else {
        countLineNumber(f);
      }
    }
  }

  private void countLineNumber(File f) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.equals("")) {
          ++lineNumber;
        }
      }
    } catch (FileNotFoundException fnfex) {
      fnfex.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
