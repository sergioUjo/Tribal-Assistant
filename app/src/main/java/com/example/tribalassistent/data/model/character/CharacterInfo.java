package com.example.tribalassistent.data.model.character;


import java.util.List;

import lombok.Data;

@Data
public class CharacterInfo {
    private Integer gold_coins;
    private Integer points;
    private Integer victory_points;
    private Object tribe_id;
    private Integer rank;
    private Integer tutorial;
    private Integer time_last_restart;
    private Boolean game_over;
    private Integer login_amount;
    private Boolean email_confirmed;
    private Integer new_messages;
    private List<Object> new_message_ids;
    private Integer new_reports;
    private List<Object> new_report_ids;
    private Integer new_posts;
    private List<Object> new_thread_ids;
    private List<Village> villages;
    private Integer chapel_village;
    private Boolean hasChapel;
    private Boolean chapel_in_queue;
    private List<Object> tribe_rights;
    private Boolean has_second_village;
}
