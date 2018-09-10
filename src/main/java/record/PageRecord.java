package record;

import utils.StringUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by luxin on 6/4/17.
 */
public class PageRecord {
    /**
     * 0:unk
     * 1:downloaded
     */
    private int AgentSite[];

    private String filename;

    private int maxSize;

    public PageRecord(String filename, int maxSize) {
        if (StringUtils.isNotEmpty(filename) && StringUtils.isNotEmpty(maxSize)) {
            this.filename = filename;
            this.maxSize = maxSize;
        } else {
            this.filename = new Date().toString();
            maxSize = 100000;
        }
        AgentSite = new int[maxSize + 1];
    }

    public void saveMatrix() {
        try {
            File file = new File("./" + filename);
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i <= maxSize; i++) {
                printWriter.println(AgentSite[i]);
            }
            printWriter.flush();
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
            System.out.println("saved!");
        } catch (IOException e) {
            System.out.println("IO ERROR");
        }
    }

    public void readMatrix() {
        try {
            int i = 0;
            FileReader reader = new FileReader("./" + filename);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
                AgentSite[i] = Integer.parseInt(str);
                i++;
            }
            System.out.println("Read Array Succ!");
        } catch (IOException e) {
            System.out.println("Read failed!");
        }
    }

    public int[] getAgentSite() {
        return AgentSite;
    }

    public void setAgentSite(int[] agentSite) {
        AgentSite = agentSite;
    }
}
