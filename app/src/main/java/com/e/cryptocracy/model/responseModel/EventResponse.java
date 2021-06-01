package com.e.cryptocracy.model.responseModel;

import java.util.List;

public class EventResponse {

    List<Event> data;
    int count;

    public List<Event> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    int page;

    public class Event {
        private String type;
        private String title;
        private String description;
        private String organizer;
        private String start_date;
        private String end_date;
        private String website;
        private String email;
        private String venue;
        private String address;
        private String city;
        private String country;
        private String screenshot;


        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getOrganizer() {
            return organizer;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public String getWebsite() {
            return website;
        }

        public String getEmail() {
            return email;
        }

        public String getVenue() {
            return venue;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public String getScreenshot() {
            return screenshot;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "type='" + type + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", organizer='" + organizer + '\'' +
                    ", start_date='" + start_date + '\'' +
                    ", end_date='" + end_date + '\'' +
                    ", website='" + website + '\'' +
                    ", email='" + email + '\'' +
                    ", venue='" + venue + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", screenshot='" + screenshot + '\'' +
                    '}';
        }
    }
}
