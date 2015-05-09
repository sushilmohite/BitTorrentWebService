/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BitTorrentWebService;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Sushil Mohite
 */
@Path("resource")
public class BitTorrentWS {
    
    List<String> trackers;
    private static final String FILE = "Trackers.txt";

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
            Logger.getLogger(BitTorrentWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of BitTorrentWebService.BitTorrentWS
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces("text/plain")
//    public String getTorrents() {
//        return "";
//    }

    /**
     * PUT method for updating or creating an instance of BitTorrentWS
     * @param fileName
     * @param fileHash
     * @param fileSize
     * @param numberOfChunks
     */
    @PUT
    @Consumes("text/plain")
    public void createTorrent(@QueryParam("fileName") String fileName, @QueryParam("fileHash") String fileHash, @QueryParam("fileSize") long fileSize, @QueryParam("numberOfChunks") int numberOfChunks) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName + ".torrent", false));
            Torrent torrent = new Torrent(fileName);
            torrent.setFileHash(fileHash);
            torrent.setFileSize(fileSize);
            torrent.setNumberOfChunks(numberOfChunks);
            torrent.setChunkSize(fileSize / numberOfChunks);
            torrent.setLastChunkSize(fileSize % numberOfChunks);
            
            Random random = new Random();
            int n = random.nextInt(trackers.size());
            String[] torrentTrackers = trackers.get(n).split(",");
            
            torrent.setTrackerIP(torrentTrackers);            
            objectOutputStream.writeObject(torrent);
            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(BitTorrentWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
