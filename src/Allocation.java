import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;

public class Allocation {
  public int[] freeSizes = new int[5];
  public int[] sizesToProcess = new int[5];
  public int[] unprocessedSizes = new int[5];

  public Allocation() {
  }

  public String[] readFile() throws Exception {
    String userDirectory = System.getProperty("user.dir");
    File file = new File(userDirectory + "\\src\\entrada.txt");
    FileReader fileReader = new FileReader(file);

    BufferedReader buffer = new BufferedReader(fileReader);

    String[] readLines = new String[2];

    for (int i = 0; i < readLines.length; i++) {
      readLines[i] = buffer.readLine();
    }

    buffer.close();

    return readLines;
  }

  public int[] formatLine(String line) throws Exception {
    String[] splittedLine = new String[5];
    int[] formattedLine = new int[5];

    splittedLine = line.split(";");

    for (int i = 0; i < splittedLine.length; i++) {
      formattedLine[i] = Integer.parseInt(splittedLine[i]);
    }

    return formattedLine;
  }

  public void readAndFormat() throws Exception {
    String[] file = new String[2];

    file = this.readFile();

    this.freeSizes = formatLine(file[0]);
    this.sizesToProcess = formatLine(file[1]);
  }

  public int[] calculateProcessedSizes() {
    return Arrays.stream(this.sizesToProcess).filter((size) -> {
      Boolean found = false;

      for (int i = 0; i < this.unprocessedSizes.length; i++) {
        if (size == this.unprocessedSizes[i])
          found = true;
      }

      return !found;
    }).toArray();
  }

  public int[] formatUnprocessedSizes() {
    return Arrays.stream(this.unprocessedSizes).filter((size) -> size != 0).toArray();
  }

  public void showInfo() {
    System.out.println("Espaços livres:" + Arrays.toString(this.freeSizes));

    System.out.println("Espaços alocados:" +
        Arrays.toString(this.calculateProcessedSizes()));

    System.out.println("Espaços não alocados:" +
        Arrays.toString(this.formatUnprocessedSizes()));
  }
}
