public class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Todo(int userId, int id, String title, boolean completed){
        this.userId=userId;
        this.id=id;
        this.title=title;
        this.completed=completed;
    }

    @Override
    public String toString(){
        return "User ID:" +userId+
        "\nID:"+id+
        "\nTitle:"+title+
        "\nCompleted:"+completed;
    }
}
