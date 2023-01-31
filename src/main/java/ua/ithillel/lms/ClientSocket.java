package ua.ithillel.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientSocket {
  private final String host;
  private final int port;

  public void prepareRequest(String body) {
    try (
        Socket clientSocket = new Socket(host, port);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
    ) {
      out.println(body);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getResponse() {
    StringBuilder sb = new StringBuilder();
    try (
        Socket clientSocket = new Socket(host, port);
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
    ) {
      String line;
      while (true) {
        if (in.available() > 0) {
          line = in.readLine();
          sb.append(line);
        }
        Thread.sleep(500);
      }
    } catch (IOException | InterruptedException e) {
      sb.append("EXCEPTION: " + e.getMessage());
    }
    return sb.toString();
  }
}
