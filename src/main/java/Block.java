/**
 * Simple block.
 *
 *
 *
 *
 */
public class Block {

    /**
     * Hash = Digital Signature.
     */
    private String hash;

    /**
     * Previous hash.
     */
    private String previousHash;

    /**
     * Data.
     */
    private String data;

    /**
     * As number of milliseconds since 1/1/1970
     */
    private long timeStamp;

    Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
    }
}