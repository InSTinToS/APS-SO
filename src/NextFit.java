public class NextFit extends Allocation {
  public void execute() throws Exception {
    initialize();

    int lastAllocatedPosition = 0;

    for (int j = 0; j < processes.length; j++) {
      for (int i = lastAllocatedPosition; i < memories.length; i++) {
        if (processes[j].value < memories[i].value) {
          allocate(processes[j], memories[i]);
          lastAllocatedPosition = i;

          break;
        } else if (i == lastAllocatedPosition - 1 || (lastAllocatedPosition == 0 && i == memories.length - 1)) {
          break;
        } else if (i == memories.length - 1)
          i = -1;
      }
    }

    end();
  }
}
