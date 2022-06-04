package allocation.allocations;

import allocation.Allocation;

public class FirstFit extends Allocation {
  public void execute() throws Exception {
    initialize();

    for (int j = 0; j < processes.length; j++) {
      for (int i = 0; i < memories.length; i++) {
        if (processes[j].value < memories[i].value) {
          allocate(processes[j], memories[i]);
          break;
        }
      }
    }

    end("First Fit");
  }
}
