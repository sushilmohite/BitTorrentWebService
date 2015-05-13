/*
 * Web Service to create .torrent files
 * 
 * @author Sushil Mohite, Kedarnath Calangutkar, Simran Jaising
 */
package BitTorrentWebService;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Sushil Mohite, Kedarnath Calangutkar, Simran Jaising
 */
@Path("resource")
public class BitTorrentWS {
    
    List<String> trackers;
    private static final String FILE = "D:\\Trackers.txt";
    private static final String PATH = "D:\\Torrents\\";
    private static final int TRACKER_PORT = 1818;

    /**
     * Creates a new instance of BitTorrentWS
     */
    public BitTorrentWS() {
        trackers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;
            while ((line = br.readLine()) != null) {
                trackers.add(line);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * PUT method for updating or creating an instance of BitTorrentWS
     * @param clientIP
     * @param fileName
     * @param fileHash
     * @param fileSize
     * @param numberOfChunks
     * @return 
     */
    @PUT
    @Consumes("text/plain")
    public String createTorrent(@QueryParam("clientIP") String clientIP, @QueryParam("fileName") String fileName, @QueryParam("fileHash") String fileHash, @QueryParam("fileSize") long fileSize, @QueryParam("numberOfChunks") int numberOfChunks) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH + fileName + ".torrent", false));
            Torrent torrent = new Torrent(fileName);
            torrent.setFileHash(fileHash);
            torrent.setFileSize(fileSize);
            torrent.setNumberOfChunks(numberOfChunks);
            torrent.setChunkSize((int)(fileSize / numberOfChunks));
            torrent.setLastChunkSize((int)((fileSize % numberOfChunks) + torrent.getChunkSize()));
            
            boolean success = false;
            String[] torrentTrackers = null;
            
            while (!success) {
                try {
                    Random random = new Random();
                    int n = random.nextInt(trackers.size());
                    torrentTrackers = trackers.get(n).split(";");
            
                    // Connect to tracker and add clients
                    String addClient = "1" + " " + torrent.getFileHash() + " " + clientIP + " " + "true";
                    Socket socket = new Socket(torrentTrackers[0], TRACKER_PORT);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    ObjectInputStream br = new ObjectInputStream(socket.getInputStream());
                    out.println(addClient);
                    success = (Boolean) br.readObject();
                    System.out.println(success);
                }
                catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            torrent.setTrackerIP(torrentTrackers);            
            objectOutputStream.writeObject(torrent);
            objectOutputStream.close();
            System.out.println(torrent.toString());
            return torrent.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
}
