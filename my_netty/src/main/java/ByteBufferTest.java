import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(88);
        String value = "ByteBuffer学习";
        buffer.put(value.getBytes());
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("remaining = " + buffer.remaining());
        buffer.flip();
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("remaining = " + buffer.remaining());
        byte[] dst = new byte[buffer.remaining()];
        buffer.get(dst);
        System.out.println(new String(dst));
    }
}
