package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.consts.ProtoConsts;
import pl.grzeslowski.jsupla.proto.TSuplaDataPacket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

public class Server implements AutoCloseable {
    private final int port;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws Exception {
        Runnable run16 = () -> {
            try (Server server = new Server(2016)) {
                server.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Runnable run15 = () -> {
            try (Server server = new Server(2015)) {
                server.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(run16).start();
        new Thread(run15).start();
    }

    private Server(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress("192.168.0.230", port));
        serverSocketChannel.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(SUPLA_MAX_DATA_SIZE + TSuplaDataPacket.SIZE);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            if (socketChannel != null) {
//                System.out.println("[" + port + "] Got new socket channel");

                buf.clear();
                final int bytesRead = socketChannel.read(buf);
//                System.out.println("Bytes read: " + bytesRead);
                final byte[] array = buf.array();
                for (int i = 0; i < array.length && i < bytesRead; i++) {
                    String s2 = String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
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
