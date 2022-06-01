
public class FirstFit extends Allocation {
  public void execute() throws Exception {
    this.readAndFormat();

    for (int j = 0; j < this.sizesToProcess.length; j++) {
      for (int i = 0; i < this.freeSizes.length; i++) {
        if (this.sizesToProcess[j] < this.freeSizes[i]) {
          this.freeSizes[i] -= this.sizesToProcess[j];

          i = this.freeSizes.length;
        } else if (i == this.freeSizes.length - 1) {
          this.unprocessedSizes[j] = this.sizesToProcess[j];
        }
      }
    }

    this.showInfo();
  }
}
