import utils.StringUtil;

import java.util.Date;

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

    /**
     * Nonce.
     */
    private int nonce;

    Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculate the hash.
     * @return hash.
     */
    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash
                        + Long.toString(timeStamp)
                        + Integer.toString(nonce)
                        + data
        );
    }

    /**
     * Mining the block.
     * @param difficulty hash difficulty.
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while ( ! hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! >>> " + getHash());
    }

    public String getHash() {
        return hash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
