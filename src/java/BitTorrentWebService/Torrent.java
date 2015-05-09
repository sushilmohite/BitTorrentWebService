/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BitTorrentWebService;

import java.io.Serializable;

/**
 *
 * @author Sushil Mohite
 */
public class Torrent implements Serializable {
    
    private String[] trackerIP;
    private final String fileName;
    private String fileHash;
    private long fileSize;
    private int numberOfChunks;
    private long chunkSize;
    private long lastChunkSize;
    
    public Torrent(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the trackerIP
     */
    public String[] getTrackerIP() {
        return trackerIP;
    }

    /**
     * @param trackerIP the trackerIP to set
     */
    public void setTrackerIP(String[] trackerIP) {
        this.trackerIP = trackerIP;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the fileHash
     */
    public String getFileHash() {
        return fileHash;
    }

    /**
     * @param fileHash the fileHash to set
     */
    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the numberOfChunks
     */
    public int getNumberOfChunks() {
        return numberOfChunks;
    }

    /**
     * @param numberOfChunks the numberOfChunks to set
     */
    public void setNumberOfChunks(int numberOfChunks) {
        this.numberOfChunks = numberOfChunks;
    }

    /**
     * @return the chunkSize
     */
    public long getChunkSize() {
        return chunkSize;
    }

    /**
     * @param chunkSize the chunkSize to set
     */
    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    /**
     * @return the lastChunkSize
     */
    public long getLastChunkSize() {
        return lastChunkSize;
    }

    /**
     * @param lastChunkSize the lastChunkSize to set
     */
    public void setLastChunkSize(long lastChunkSize) {
        this.lastChunkSize = lastChunkSize;
    }
}
