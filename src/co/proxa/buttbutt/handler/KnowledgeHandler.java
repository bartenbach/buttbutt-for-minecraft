package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.util.StringUtils;

import java.sql.SQLException;

public class KnowledgeHandler {

    private Buttbutt butt;

    public KnowledgeHandler(Buttbutt butt) {
        this.butt = butt;
    }

    public void addKnowledge(String commandSender, String[] data) {
        if (data[1].endsWith(":") && data.length > 2) {
            String command = StringUtils.getArgs(data);
            String[] split = command.split(":");
            String item = split[0].substring(0, split[0].length() - 1);
            System.out.println("Item: " + item);
            System.out.println("Data: " + split[1]);
            try {
                butt.getKnowledgeTable().insertKnowledge(item, split[1], commandSender);
            } catch (SQLException ex) {
                butt.getLogger().warning("Failed to add new knowledge to buttbutt database");
            }
        }
    }

    public String getKnowledge(String[] item) {
        if (item.length > 0) {
            String itemName = StringUtils.getArgs(item);
            String[] fullItem = itemName.split("~");
            System.out.println("Item: " + fullItem[1]);
            return butt.getKnowledgeTable().queryKnowledge(fullItem[1]);
        }
        return null;
    }

    public boolean removeKnowledge(String[] item) {
        return item.length > 0 && butt.getKnowledgeTable().deleteKnowledge(StringUtils.concatenateArgs(item));
    }
}
