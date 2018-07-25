package rech.bolao.bean.xmlstats;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Events {

	@JsonProperty("events_date")
	private String eventsDate;

	@JsonProperty("count")
	private Integer eventsCount;

	@JsonProperty("event")
	private ArrayList<Event> eventList;

	public Events() {
	}

	public String getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(String eventsDate) {
		this.eventsDate = eventsDate;
	}

	public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

	public Integer getEventsCount() {
		return eventsCount;
	}

	public void setEventsCount(Integer eventsCount) {
		this.eventsCount = eventsCount;
	}
}
