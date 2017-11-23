package dass.vikeshkumar.intelimentapplication.utility;

public class TransportInfo {

    public String getName() {
        return name;
    }

    public fromcentral getFromcentral() {
        return fromcentral;
    }

    public location getLocation() {
        return location;
    }

    private String id;
    private String name;
    private fromcentral fromcentral;
    private location location;


    public class fromcentral {
        public String getCar() {
            return car;
        }

        public String getTrain() {
            return train;
        }

        private String car;
        private String train;
    }

    public class location {
        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        private String latitude;
        private String longitude;
    }

}
