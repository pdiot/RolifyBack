package com.rolify.chat;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="Message_id")
public class MessageGlobal extends Message {
	

}
