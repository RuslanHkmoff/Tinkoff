package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class ChainOfWriters implements AutoCloseable {
    private final PrintWriter printWriter;
    private final OutputStream outputStream;

    private OutputStreamWriter outputStreamWriter;
    private BufferedOutputStream bufferedOutputStream;
    private CheckedOutputStream checkedOutputStream;

    public ChainOfWriters(Path path) throws IOException {
        this.outputStream = Files.newOutputStream(path);
        this.printWriter = setOutputStreamWriter();
    }

    public void write(String data) {
        printWriter.write(data);
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
        outputStreamWriter.close();
        bufferedOutputStream.close();
        checkedOutputStream.close();
        outputStream.close();
    }

    private PrintWriter setOutputStreamWriter() {
        this.outputStreamWriter = setBufferedWriter();
        return new PrintWriter(outputStreamWriter);
    }

    private OutputStreamWriter setBufferedWriter() {
        this.bufferedOutputStream = setCheckedStream();
        return new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
    }

    private BufferedOutputStream setCheckedStream() {
        this.checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
        return new BufferedOutputStream(checkedOutputStream);
    }
}
