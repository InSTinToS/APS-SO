import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;

public class Allocation {
  public NamedObject[] memories;
  public NamedObject[] processes;
  public ArrayList<NamedObject> unallocatedProcesses;
  public ArrayList<NamedObject> allocatedProcesses = new ArrayList<NamedObject>();

  public int largestFreeMemory = 0;
  public int freeMemoryOverage = 0;
  public int smallestFreeMemory = 0;

  public Allocation() {
  }

  private String[] readFile() {
    try {
      String[] readLines = new String[2];
      String userDirectory = System.getProperty("user.dir");
      File file = new File(userDirectory + "\\src\\entrada.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader buffer = new BufferedReader(fileReader);

      for (int i = 0; i < readLines.length; i++)
        readLines[i] = buffer.readLine();

      buffer.close();

      return readLines;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  private NamedObject[] formatLine(String line, String prefix) {
    String[] splittedLine = line.split(";");
    NamedObject[] formattedLine = new NamedObject[splittedLine.length];

    for (int i = 0; i < splittedLine.length; i++) {
      int value = Integer.parseInt(splittedLine[i]);
      String name = prefix + (i + 1);

      NamedObject newNamedObject = new NamedObject(name, value);
      formattedLine[i] = newNamedObject;
    }

    return formattedLine;
  }

  private void printSeparately(NamedObject[] array, String label) {
    String[] names = new String[array.length];
    int[] values = new int[array.length];

    for (int i = 0; i < names.length; i++) {
      names[i] = array[i].name;
      values[i] = array[i].value;
    }

    System.out.println("\n" + label.substring(0, 1).toUpperCase() + label.substring(1));
    System.out.println("Nomes: " + Arrays.toString(names));
    System.out.println("Valores: " + Arrays.toString(values));
    System.out.println("Array: " + Arrays.toString(array));
  }

  private void calculateMemoryInfo() {
    int sum = 0;
    int noFreeSize = 0;

    largestFreeMemory = memories[0].value;
    smallestFreeMemory = memories[0].value;

    for (int i = 1; i < memories.length; i++) {
      if (memories[i].value == 0)
        noFreeSize++;
      else {
        if (memories[i].value > largestFreeMemory)
          largestFreeMemory = memories[i].value;

        if (memories[i].value < smallestFreeMemory)
          smallestFreeMemory = memories[i].value;

        sum += memories[i].value;
      }
    }

    freeMemoryOverage = sum / (memories.length - noFreeSize);
  }

  private void showInfo() {
    printSeparately(processes, "processos");
    printSeparately(allocatedProcesses.toArray(NamedObject[]::new), "processos alocados");
    printSeparately(unallocatedProcesses.toArray(NamedObject[]::new), "processos não alocados");
    printSeparately(memories, "memória livre");

    System.out.println("\nMaior memória livre: " + largestFreeMemory);
    System.out.println("Menor memória livre: " + smallestFreeMemory);
    System.out.println("Média de memórias livres: " + freeMemoryOverage);
  }

  public void initialize() {
    String[] file = new String[2];

    file = readFile();

    memories = formatLine(file[0], "M");
    processes = formatLine(file[1], "P");

    unallocatedProcesses = new ArrayList<NamedObject>(Arrays.asList(processes));
  }

  public void allocate(NamedObject process, NamedObject memory) {
    memory.value -= process.value;

    unallocatedProcesses.remove(process);

    allocatedProcesses.add(process);
  }

  public void end() {
    calculateMemoryInfo();
    showInfo();
  }
}
