package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.proto.TSuplaDataPacket;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import static java.lang.String.format;

public class Server implements AutoCloseable {
    private final int port;
    private ServerSocketChannel serverSocketChannel;

    private Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        try (Server server = new Server(2016)) {
            server.startHttpsServer();
        }
    }

    private void startHttpsServer() throws Exception {
        System.setProperty("javax.net.ssl.keyStore", Server.class.getClassLoader().getResource("jsupla-keystore").getFile());
        System.setProperty("javax.net.ssl.keyStorePassword","jSuplaPwd");

        System.out.println("Starting server on port " + port);
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(port);

        while (true) {
            Socket s = ss.accept();
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();

            byte[] bytes = new byte[16 * 1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                final byte[] array = Arrays.copyOf(bytes, count);
                for (int i = 0; i < array.length; i++) {
                    System.out.print(format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0')); // 00000010
//                    System.out.printf("%4d", array[i]);
                    System.out.print(" ");
                }
            }
        }
    }

    private void start() throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress("192.168.0.230", port));
        serverSocketChannel.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(TSuplaDataPacket.SIZE);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            if (socketChannel != null) {
//                System.out.println("[" + port + "] Got new socket channel");

                buf.clear();
                final int bytesRead = socketChannel.read(buf);
//                System.out.println("Bytes read: " + bytesRead);
                final byte[] array = buf.array();
                for (int i = 0; i < array.length && i < bytesRead; i++) {
                    String s2 = format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
                    System.out.print(s2); // 00000010
//                    System.out.printf("%4d", array[i]);
                    System.out.print(" ");
                }
                System.out.println("");
//                for (byte aByte : array) {
//                    System.out.print((char) aByte);
//                    System.out.print(" ");
//                }
//                System.out.println("");
            }
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing server");
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }
    }
}
