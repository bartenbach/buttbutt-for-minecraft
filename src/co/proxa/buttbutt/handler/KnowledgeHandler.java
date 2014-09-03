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
            String item = data[1].substring(0, data[1].length()-1);
            String description = StringUtils.getArgsOverOne(data);
            try {
                butt.getKnowledgeTable().insertKnowledge(item, description, commandSender);
            } catch (SQLException ex) {
                butt.getLogger().warning("Failed to add new knowledge to buttbutt database");
            }
        }
    }

    public String getKnowledge(String[] item) {
        if (item.length > 0) {
            return butt.getKnowledgeTable().queryKnowledge(StringUtils.concatenateArgs(item));
        }
        return null;
    }

    public boolean removeKnowledge(String[] item) {
        return item.length > 0 && butt.getKnowledgeTable().deleteKnowledge(StringUtils.concatenateArgs(item));
    }
}
