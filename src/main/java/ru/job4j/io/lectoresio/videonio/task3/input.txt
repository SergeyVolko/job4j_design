package ru.job4j.io.lectoresio.videonio.task1;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        String path = "src/main/java/ru/job4j/io/lectoresio/videonio/file.txt";
        /*
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(path),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)){
            ByteBuffer buffer = ByteBuffer.allocate(26);
            for (int i = 0; i < 26; i++) {
                buffer.put((byte) ('A' + i));
            }
            buffer.rewind();
            channel.write(buffer);
        } catch (IOException exception) {
            System.out.println("Input / Output error");
        }
        */
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(path),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE)) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            for (int i = 0; i < 26; i++) {
                buffer.put((byte) ('A' + i));
            }
        } catch (IOException e) {
            System.out.println();
        }

    }
}
