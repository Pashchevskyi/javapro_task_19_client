package ua.ithillel.lms;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    ClientSocket clientSocket = new ClientSocket("localhost", 8080);
    clientSocket.prepareRequest("-exit");
    String response = clientSocket.getResponse();
    System.out.println(response);
  }
}
