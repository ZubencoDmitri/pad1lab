package xPlatform;


public interface IOperation {
    public String readAsync();
    public void writeAsync(String message);

    public String deserialzeMessageJDK7(String filename);
}
