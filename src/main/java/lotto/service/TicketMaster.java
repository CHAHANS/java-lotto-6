package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Customer;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class TicketMaster {
    Lotto winningNum;
    int bonusNum;

    public void makeTicket(Customer cs) {
        int coin = cs.getWallet();
        int ticketSize = coin / 1000;
        List<Lotto> newLotto = new ArrayList<>();
        for (int i = 0; i < ticketSize; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            newLotto.add(new Lotto(numbers));
        }
        cs.setCustomerLotto(newLotto);
    }

    public int checkLotto(Lotto lotto) {
        List<Integer> thisLotto = lotto.getNumbers();
        List<Integer> winnerLotto = winningNum.getNumbers();
        int result = 0;
        for (int i = 0; i<winningNum.getNumbers().size(); i++){
            if (winnerLotto.contains(thisLotto.get(i))){
                result++;
            }
        }
        if (thisLotto.contains(bonusNum)){
            result++;
        }
        return result;
    }

    public void setWinningNum(Lotto winningNum) {
        this.winningNum = winningNum;
    }

    public void setBonusNum(int bonusNum) {
        validateBonusNum(bonusNum);
        this.bonusNum = bonusNum;
    }

    private void validateBonusNum(int num) {
        if (winningNum.getNumbers().contains(num)) {
            throw new IllegalArgumentException("보너스 번호가 당첨번호와 중복됩니다.");
        }
    }
}
