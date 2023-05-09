import java.util.*
import kotlin.math.log10

// Silver 2
class Problem1874 {

    private var orderNum = 1
    private val myStack = Stack<Int>()
    private val answerStr = StringBuilder()

    // silver 2
    fun solve() {
        val size = readln().toInt()
        for (i in 0 until size) {
            val num = readln().toInt()
            isSuccess(size, num)
        }

        // 스택이 비어 있지 않으면 수열 만들기 실패
        if (myStack.isEmpty()) print(answerStr)
        else print("NO")
    }

    private fun isSuccess(size: Int, num: Int){
        // orderNum이 size(최대값)보다 크다는 것은 수열 반환에 실패한 것
        while (myStack.size <= size) {
            // 스택이 비어있으면 연속된 orderNum을 push한다.
            if (myStack.isEmpty()) {
                myStack.push(orderNum++)
                answerStr.append("+\n")
            }

            if (myStack.peek() == num) { // 같은게 나오면 pop
                myStack.pop()
                answerStr.append("-\n")
                break
            }
            else {                      // 반대로 다른게 나오면 push
                myStack.push(orderNum++)
                answerStr.append("+\n")
            }
        }
    }
}


// Silver 5(Greedy) - 40min
class Problem14916 {
    fun solve() {

        val money = readln().toInt()
        val coinNum5 = money / 5
        var coinNum2 = 0
        var answerCoinNum = 0

        // money가 짝수면 5를 짝수 배로
        if(money % 2 == 0) {
            if(coinNum5 % 2 == 0) {
                coinNum2 = (money % 5) / 2
                answerCoinNum = coinNum5 + coinNum2
            }
            // 5원 동전을 하나 빼고 계산한다.
            else {
                coinNum2 = (money % 5 + 5) / 2
                answerCoinNum = coinNum5-1 + coinNum2
            }
        }
        // money가 홀수면 5를 홀수 배로
        else {
            // 5원 동전을 하나 뺴고, 계산한다.
            if(coinNum5 % 2 == 0) {
                coinNum2 = (money % 5 + 5) / 2
                answerCoinNum = coinNum5-1 + coinNum2
            }
            else {
                coinNum2 = (money % 5) / 2
                answerCoinNum = coinNum5 + coinNum2
            }
        }

        if(money < 5 && money % 2 != 0)
            answerCoinNum = -1


        // 짝수 - 짝수 - 짝수 = 짝수 Or 짝수 - 홀수 - 짝수 = 홀수
        // 홀수 - 짝수 - 짝수 = 홀수 Or 홀수 - 홀수 - 짝수 = 짝수
        // 즉, money가 짝수면 5를 짝수로, money가 홀수면 5를 홀수로 만들어주면 됨.
        // 단, money가 5 미만이면 조절을 못함. 그 경우만 -1을 리턴

        print(answerCoinNum)
    }
}

// Silver 4(Greedy) - 28min
class Problem2217 {
    fun solve() {
        val n = readln().toInt()
        val lopeList = mutableListOf<Int>()
        for(i in 0 until n) {
            val lope = readln().toInt()
            lopeList.add(lope)
        }
        lopeList.sortDescending() // 내림차순 정렬

        var weight = 0
        var maxWeight = 0
        for(i in lopeList.indices) {
            weight = lopeList[i] * (i+1)
            if(maxWeight < weight)
                maxWeight = weight
        }

        print(maxWeight)
    }
}

// Silver 4(Greedy) - 40 min
class Problem1758 {
    fun solve() {
        val n = readln().toInt()
        val tipList = mutableListOf<Int>()
        for(i in 0 until n) {
            val tip = readln().toInt()
            tipList.add(tip)
        }
        tipList.sortDescending()    // 내림차순 정렬
        val sumOfTip = getSumOfTip(n, tipList)
        print(sumOfTip)
    }

    private fun getSumOfTip(n: Int, tipList: List<Int>): Long {
        var sumOfTip: Long = 0
        for(i in 0 until n) {
            var decidedTip = tipList[i] - (i+1 - 1)
            if(decidedTip < 0)
                decidedTip = 0

            sumOfTip += decidedTip
        }
        return sumOfTip
    }
}

// Silver 3(Greedy) - 1 hour over.. (58점)
class Problem13305 {
    private val scr = Scanner(System.`in`)
    fun solve() {
        val n = scr.nextInt()
        val oilPriceList = mutableListOf<Int>()
        val distanceList = mutableListOf<Int>()

        // input
        input(n, oilPriceList, distanceList)

        var currentOilPrice = oilPriceList[0]
        var answerSum: Long = 0
        for(i in 1 until n) {
            // 다음 도시로 이동. 후 오일 가격을 바꿔준다.
            if(currentOilPrice > oilPriceList[i]) {
                answerSum += currentOilPrice * distanceList[i-1].toLong()
                currentOilPrice = oilPriceList[i]
            }
            // 현재 오일이 다음 것보다 작으면 그 다음 도시까지 간다.
            else {
                answerSum += currentOilPrice * distanceList[i-1].toLong()
            }
        }

        print(answerSum)

    }

    private fun input(
        n: Int,
        oilPriceList: MutableList<Int>,
        distanceList: MutableList<Int>
    ) {
        for (i in 0 until n - 1) {
            val distance = scr.nextInt()
            distanceList.add(distance)
        }

        for (i in 0 until n) {
            val oilPrice = scr.nextInt()
            oilPriceList.add(oilPrice)
        }
    }

}

// Silver 3 - 25 min (Greedy)
class Problem20115 {
    fun solve() {
        val scr = Scanner(System.`in`)
        val n = scr.nextLong()
        val amountList = mutableListOf<Long>()
        for(i in 0 until n) amountList.add(scr.nextLong())

        // 제일 큰 용량을 제외한 값들의 합 / 2를 해준다. (결합법칙)
        val restAmount = (amountList.sum() - amountList.max()).toDouble() / 2
        // 제일 큰 용량 + 절반씩 잃은 것들의 합
        val answer = amountList.max() + restAmount
        print(answer)
    }
}

// Silver 2 - 35 min (Greedy)
class Problem1541 {
    fun solve() {
        var answerSum = 0
        val str = readln()
        val numList = str.split('+', '-')
        val signList = mutableListOf<Char>()
        for(ch in str) {
            if (ch == '+' || ch == '-')
                signList.add(ch)
        }

        answerSum = numList[0].toInt()
        for(i in signList.indices) {
            if(signList[i] == '+')
                answerSum += numList[i+1].toInt()
            else {
                answerSum -= minusSum(numList, i+1)
                break
            }
        }

        print(answerSum)
    }

    private fun minusSum(numList: List<String>, startIndex: Int): Int {
        var sum = 0
        for(i in startIndex until numList.size) {
            sum += numList[i].toInt()
        }
        return sum
    }
}

// Silver 2 - 50 min (Greedy)
class Problem16953 {
    fun solve() {
        var answerCnt = 1
        val str = readln().split(" ")
        val a = str[0].toInt()
        var b = str[1].toInt()

        while(b > a) {
            val lastNumber = b % 10 // 일의 자리 숫자 구하기
            if(lastNumber == 1) { // 마지막 수가 1인 경우
                b = b / 10 // 일의 자리 수 없애기
            }
            else {
                if(b % 2 == 0)
                    b /= 2
                else
                    break   // 2로 나눌 수 없을 경우 실패
            }
            answerCnt++
        }

        // 결국 b를 a로 만드는데 성공했다면 성공!
        if(a == b)
            print(answerCnt)
        else
            print("-1")
    }
}

class Problem1931 {
    fun solve() {
        var answerCount = 0
        val n = readln().toInt()
        var timeTable = mutableMapOf<Int, Int>()
        for(i in 0 until n) {
            val str = readln().split(" ")
            val startTime = str[0].toInt()
            var endTime = str[1].toInt()

            // 시작 시간이 같은 회의라면
            timeTable[startTime]?.let {
                // 끝나는 시간이 더 빨리 끝난다면, 원래 값을 유지시켜준다.
                if(it < endTime)
                    endTime = it
            }
            timeTable[startTime] = endTime
        }

        val lastMeeting = timeTable.maxBy { it.value }.value
        // 전 회의의 끝나는 시간이 제일 마지막 회의시간의 끝나는 시간과 같아지면 종료
        do {
            val lastMeetingEndTime = timeTable.minBy { it.value }.value // endTime이 가장 작은 값을 가져온다.
            // 전 회의시간의 끝나는 시간보다 늦게 시작하는 회의만을 골라내 timeTable을 만든다.
            timeTable = getNewTimeTable(timeTable, lastMeetingEndTime)
            // 회의가 끝나는 시간이 가장 작은 회의를 골라낸다.
            answerCount++
        } while(lastMeetingEndTime != lastMeeting)

        print(answerCount)
    }

    private fun getNewTimeTable(oldMap: Map<Int, Int>, minValue: Int):MutableMap<Int, Int> {
        val newMap = mutableMapOf<Int, Int>()
        for(startTime in oldMap.keys) {
            // 전 회의시간의 끝나는 시간보다 시작시간이 같거나 큰 회의만 추가
            if(startTime >= minValue) {
                newMap.put(startTime, oldMap[startTime]!!)
            }
        }

        return newMap
    }
}