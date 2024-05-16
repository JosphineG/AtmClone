package AtmClone;
import java.util.Date;
public class Transaction {
    private double amount;

    private Date timestamp;

    private String memo;

    private Account inAccount;

    public  Transaction (double Amount, Account inAccount){
        this.amount =Amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }
    public  Transaction (double Amount,String memo, Account inAccount){
        this(Amount, inAccount);
        this.memo = memo;
    }


    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s",this.timestamp.toString(),
                    this.amount,this.memo);
        } else {
            return String.format("%s : $(.02f) : %s",
                    this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
