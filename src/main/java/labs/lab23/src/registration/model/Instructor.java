package labs.lab23.src.registration.model;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    int[] canTeach;

    public void setCanTeach(int[] canTeach){
        this.canTeach = canTeach;
    }

    public int[] getCanTeach(){
        return canTeach;
    }
}
