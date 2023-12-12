package moduleFilm;

public class Film {
    private int id;
    private String title;
    private String channel;
    private String genre;
    private String dayOfWeek;
    private String startTime;

    public Film() {
    }

    public Film(int id, String title, String channel, String genre, String dayOfWeek, String startTime) {
        this.id = id;
        this.title = title;
        this.channel = channel;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "\nПрограмма{" +
                "id=" + id +
                ",название='" + title + '\'' +
                ",канал='" + channel + '\'' +
                ",жанр='" + genre + '\'' +
                ",деньНедели='" + dayOfWeek + '\'' +
                ",времяНачала='" + startTime + '\'' +
                "}";
    }
}
