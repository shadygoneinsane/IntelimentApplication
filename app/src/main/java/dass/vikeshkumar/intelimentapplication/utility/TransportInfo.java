package dass.vikeshkumar.intelimentapplication.utility;

/**
 * Created by vikesh on 22-11-2017.
 */
public class TransportInfo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public fromcentral getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(fromcentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    public location getLocation() {
        return location;
    }

    public void setLocation(location location) {
        this.location = location;
    }

    private String id;
    private String name;
    private fromcentral fromcentral;
    private location location;


    public TransportInfo(String id, String name, fromcentral fromcentral, location location) {
        this.id = id;
        this.name = name;
        this.fromcentral = fromcentral;
        this.location = location;
    }

    public class fromcentral {
        public String getCar() {
            return car;
        }

        public void setCar(String car) {
            this.car = car;
        }

        public String getTrain() {
            return train;
        }

        public void setTrain(String train) {
            this.train = train;
        }

        private String car;
        private String train;
    }

    public class location {
        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        private String latitude;
        private String longitude;
    }

}
