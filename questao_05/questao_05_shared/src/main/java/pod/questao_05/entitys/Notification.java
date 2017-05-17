package pod.questao_05.entitys;

import pod.questao_05.interfaces.Subscriber;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/05/2017, 21:54:33
 */
public class Notification {

    private Long id;
    private Message msg;
    private Subscriber[] subs;

    public Notification() {
    }

    public Notification(Message msg) {
        this.msg = msg;
    }
    
    public Notification(Message msg, Subscriber[] subs) {
        this.msg = msg;
        this.subs = subs;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public Subscriber[] getSubs() {
        return subs;
    }

    public void setSubs(Subscriber[] subs) {
        this.subs = subs;
    }

}
