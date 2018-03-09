import java.util.ArrayList;
import com.google.gson.GsonBuilder;


/**
 * Application.
 *
 *
 *
 *
 */
public class Blockchain {

    /**
     * Basic blockchain implementation.
     */
    private static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        Blockchain.blockchain.add(new Block("One Block", "0"));
        Blockchain.blockchain.add(new Block("Two Block", blockchain.get(blockchain.size() - 1).getHash()));
        Blockchain.blockchain.add(new Block("Three Block", blockchain.get(blockchain.size() - 1).getHash()));
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Blockchain is valid: " + isChainValid());
        System.out.println(blockchainJson);
    }

    /**
     * Check for valid chain.
     * @return approval.
     */
    private static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); ++i) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if ( ! currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }
            if ( ! previousBlock.getHash().equals(previousBlock.calculateHash())) {
                System.out.println("Previous hashes not equal");
                return false;
            }
        }

        return true;
    }
}
