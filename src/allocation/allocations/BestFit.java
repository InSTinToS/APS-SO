package allocation.allocations;

import allocation.Allocation;
import allocation.NamedObject;

public class BestFit extends Allocation {
  public void execute() throws Exception {
    initialize();

    for (int j = 0; j < processes.length; j++) {
      NamedObject smallest = new NamedObject("unallocated", Integer.MAX_VALUE);

      for (int i = 0; i < memories.length; i++) {
        if (processes[j].value < memories[i].value) {
          if (memories[i].value < smallest.value)
            smallest = new NamedObject(memories[i].name, memories[i].value);
        }
      }

      if (smallest.name != "unallocated")
        for (int i = 0; i < memories.length; i++) {
          if (memories[i].name == smallest.name) {
            allocate(processes[j], memories[i]);
            break;
          }
        }
    }

    end("Best Fit");
  }
}
