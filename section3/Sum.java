package com.company.section3;

public class Sum  implements  Expression {
    Expression augmend;
    Expression addmend;



    public Sum(Money augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce( Bank bank, String to){
        int amount = augmend.reduce(bank, to).amount + addmend.reduce(bank, to).amount;
        return new Money(amount, to);

    }

    @Override
    public Expression plus(Expression addend) {
        return null;
    }
}
