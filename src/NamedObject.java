
public class NamedObject {
  public String name;
  public int value;

  public NamedObject(String name, int value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return "{name: " + name + ", value: " + value + "}";
  }
}
