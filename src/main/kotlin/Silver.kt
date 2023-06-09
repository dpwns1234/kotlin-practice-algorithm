import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

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


// Silver 5(Greedy) - 40min (resolve 30min)
class Problem14916 {
    fun solve() {
        val money = readln().toInt()

        var coinNum = money / 5
        var restMoney = money % 5
        if(restMoney % 2 == 0)
            coinNum += (restMoney / 2)
        else {
            coinNum -= 1 // 5원을 덜 사용했기 떄문에
            restMoney += 5
            coinNum += (restMoney / 2)
        }

        if(money == 1 || money == 3)
            coinNum = -1

        print(coinNum)
    }
}

// Silver 4(Greedy) - 28min (resolve 15min)
class Problem2217 {
    fun solve() {
        val n = readln().toInt()
        val lopeList = mutableListOf<Int>()
        for(i in 0 until n) {
            val lope = readln().toInt()
            lopeList.add(lope)
        }
        lopeList.sortDescending() // 내림차순 정렬
        for(i in lopeList.indices) {
            lopeList[i] *= (i+1)
        }
        val maxWeight = lopeList.max()
        print(maxWeight)
    }
}

// Silver 4(Greedy) - 40 min (resolve 22min)
class Problem1758 {
    fun solve() {
        var answerTip: Long = 0
        val tipList = mutableListOf<Long>()
        val n = readln().toInt()
        for(i in 0 until n) {
            tipList.add(readln().toLong())
        }
        tipList.sortDescending()

        for(index in tipList.indices) {
            val actualTip = tipList[index] - index // 원래는 순서 - 1 이지만 index 가 0 부터 시작이므로 (order-1)로 바로 사용 가능.
            if(actualTip <= 0) // 내림차순으로 정렬했으므로 뒤의 팁들도 모두 0원처리가 돼서 의미없는 반복문을 종료한다.
                break

            answerTip += actualTip
        }
        print(answerTip)
    }
}

// Silver 3(Greedy) - 1 hour over.. (resolve 25min)
class Problem13305 {
    fun solve() {
        var costSum: Long = 0
        val n = readln().toInt()
        val distanceList = readln().split(" ")
        val oilCostList = readln().split(" ")

        var lowCost = oilCostList[0].toLong()
        for(i in distanceList.indices) {
            val oilCost = oilCostList[i].toLong()
            val distance = distanceList[i].toLong()
            if(oilCost < lowCost) {
                lowCost = oilCost
            }

            costSum += (lowCost * distance)
        }
        print(costSum)
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

// Silver 2 - 35 min (Greedy) (resolve 20min)
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

// Silver 2 - 50 min (Greedy) (resolve 20min)
class Problem16953 {
    fun solve() {
        var answer = 1
        val str = readln().split(" ")
        val a = str[0].toInt()
        var b = str[1].toInt()

        // a == b가 되면 변환 성공
        while(a != b) {
            // b가 a보다 작아지면 변환 실패
            if (b < a){
                answer = -1
                break
            }
            // 일의 자리가 1이냐
            else if(b%10 == 1)
                b /= 10
            // 짝수냐
            else if(b%2 == 0)
                b /= 2
            // 1이 아닌 홀수일 경우 변환 실패
            else {
                answer = -1
                break;
            }
            answer++
        }

        print(answer)
    }

    fun solve2() {
        val str = readln().split(" ")
        val a = str[0].toInt()
        val b = str[1].toInt()

        var value = b
        var cnt = 1
        while(value > a) {
            if(value % 10 == 1) {
                value /= 10
            }
            else if(value % 2 == 0) {
                value /= 2
            }
            else {
                print(-1)
                return
            }
            cnt++
        }

        if(value == a) print(cnt)
        else print(-1)
    }
}

// Silver 1 - 1hour 30min (Greedy)
class Problem1931 {
    fun solve() {
        var answerCount = 1
        val timeTable = input()
        timeTable.sortWith(compareBy ({ it.second }, {it.first})) // 끝나는 시간이 작은 순으로 정렬 + 시작 시간이 같으면 그것도 오름차순으로 정렬

        var previousMeeting = timeTable[0]
        // 전 회의의 끝나는 시간이 제일 마지막 회의시간의 끝나는 시간과 같아지면 종료
        for(index in 1 until timeTable.size) {
            val meetingStartTime = timeTable[index].first
            // 전 미팅의 끝나는 시간이 그 다음 미팅 시작시간이랑 겹치지 않으면 그 회의로 결정!
            if (previousMeeting.second > meetingStartTime) // 시간이 겹침
                continue
            else {
                previousMeeting = timeTable[index]
                answerCount++
            }
        }
        print(answerCount)
    }

    private fun input(): MutableList<Pair<Int, Int>> {
        val n = readln().toInt()
        val timeTable = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until n) {
            val str = readln().split(" ")
            val startTime = str[0].toInt()
            val endTime = str[1].toInt()

            val meeting = Pair(startTime, endTime)
            timeTable.add(meeting)
        }
        return timeTable
    }
}

// Silver2 - 2 hour (Greedy)
class Problem21314 {
    private val ZERO = "0"
    private val ONE = "1"
    fun solve() {
        val str = readln()
        val maxMK = getMaxMK(str)
        val minMK = getMinMK(str)
        println(maxMK + "\n" + minMK)

    }

    private fun getMaxMK(str:String): String {
        val MKNumber = StringBuilder("")
        var cnt = 0

        for(ch in str) {
            if(ch == 'M') {
                cnt++
            }
            else if(ch == 'K') {
                val temp = "5" + ZERO.repeat(cnt)
                MKNumber.append(temp)
                cnt = 0
            }
        }
        if(str.last() == 'M') {
            val temp = ONE.repeat(cnt)
            MKNumber.append(temp)
        }

        return MKNumber.toString()
    }
    private fun getMinMK(str: String): String {
        val MKNumber = StringBuilder("")
        var flag = false
        for(ch in str) {
            if(ch == 'M' && !flag) {
                MKNumber.append('1')
                flag = true
            }
            else if (ch == 'M' && flag) {
                MKNumber.append('0')
            }
            else if (ch == 'K') {
                MKNumber.append('5')
                flag = false
            }
        }

        return MKNumber.toString()
    }
}

// Silver5 - 40min (Binary Search)
class Problem1789 {
    fun solve() {
        val S = readln().toLong()
        var start: Long = 1
        var end: Long = S
        var mid: Long = 0
        while(end >= start) {
            mid = (start+end)/2
            val sigmaMid = mySum(mid)
            if(sigmaMid < S) start = mid+1
            else if(sigmaMid > S) end = mid-1
            else {
                print(mid)
                return
            }
        }
        print(end)
    }

    private fun mySum(n: Long): Long {
        var sum: Long = 0
        for(i in 1..n)
            sum += i

        return sum
    }
}

// Silver5 - 30min(Binary Search)
class Problem10815 {
    fun solve() {
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val firstSet = mutableSetOf<Int>()
        for(i in 0 until n)
            firstSet.add(str1[i].toInt())

        val m = readln().toInt()
        val str2 = readln().split(" ")
        val secondList = mutableListOf<Int>()
        for(i in 0 until m)
            secondList.add(str2[i].toInt())

        for(element in secondList) {
            if(firstSet.contains(element)) print("1 ")
            else print("0 ")
        }
    }

    private fun binarySearch(list: List<Int>, target: Int): Boolean {
        var start = 0
        var end = list.lastIndex
        while(start <= end) {
            val mid = (start+end)/2
            if(target > list[mid]) start = mid+1
            else if (target < list[mid]) end = mid-1
            else  return true // target을 찾은 경우
        }
        return false
    }
}

// Silver3 - 40min (Binary Search유형이지만, 그냥 풀음)
class Problem2512 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str = readln().split(" ")
        val requiredBudget = mutableListOf<Int>()
        for(budget in str) {
            requiredBudget.add(budget.toInt())
        }
        val totalBudget = readln().toInt()

        requiredBudget.sortDescending() // 큰 순서대로 정렬
        var maginotBudget = 0
        var repeatNum = 0
        var budgetSum = requiredBudget.sum()
        for(maxBudget in requiredBudget) {
            budgetSum += (maxBudget * repeatNum)
            if(totalBudget >= budgetSum) { // 총예산이 더 많다 -> 여기까지 원하는만큼 줄 수 있다.
                maginotBudget = maxBudget
                break
            }

            repeatNum++
            budgetSum -= (maxBudget*repeatNum) // -n번째 max
        }
        if(repeatNum == 0) {
            print(maginotBudget)
            return
        }

        val answerBudget = maginotBudget + (totalBudget - budgetSum) / repeatNum
        print(answerBudget)
    }
}

// Silver2 - 2 hour (Binary Search)
class Problem1654 {
    fun solve() {
        val str = readln().split(" ")
        val K = str[0].toInt()
        val N = str[1].toLong()
        val lenList = mutableListOf<Int>()
        for(i in 0 until K) {
            lenList.add(readln().toInt())
        }

        var start:Long = 0
        var end:Long = lenList.max().toLong() + 1 // mid가 0가 되는 상황을 막아줌.
        while(start < end) {
            val mid = (start+end) / 2
            val countSum = calculateCountSum(lenList, mid)
            if(countSum > N)
                start = mid+1
            else if(countSum < N)
                end = mid
            else {
                start = mid+1
            }
        }

        print(start-1)
    }

    fun solve2() {
        val str = readln().split(" ")
        val k = str[0].toInt()
        val n = str[1].toInt()
        val lengths = mutableListOf<Int>()
        repeat(k) {
            lengths.add(readln().toInt())
        }
        lengths.sort()
        val maxLength = upperBoundBS(lengths, n)
        print(maxLength)
    }

    private fun upperBoundBS(lenList: List<Int>, n: Int): Long {
        var st = 1L
        var en: Long = lenList.last().toLong() + 1
        while(st < en) {
            val mid = (st + en) / 2L
            val cnt = calculateCountSum(lenList, mid)
            if(cnt < n) {
                en = mid
            }
            else {
                st = mid + 1
            }
        }
        return st-1
    }

    private fun calculateCountSum(lenList: List<Int>, mid: Long): Long {
        var cnt:Long = 0
        for(len in lenList) {
            cnt += (len / mid)
        }
        return cnt
    }
}

// Silver4 - 10min (Binary Search)
class Problem1920 {
    fun solve() {
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val nList = mutableListOf<Int>()
        for(e in str1) {
            nList.add(e.toInt())
        }
        val m = readln().toInt()
        val mList = readln().split(" ")

        nList.sort()
        for(e in mList) {
            if(isExist(nList, e.toInt())) println("1")
            else println("0")
        }
    }

    private fun isExist(nList: List<Int>, element: Int): Boolean {
        var st = 0
        var en = nList.lastIndex
        while(st <= en) {
            val mid = (st+en) / 2
            if(nList[mid] > element) {
                en = mid - 1
            }
            else if(nList[mid] < element) {
                st = mid + 1
            }
            else {
                return true
            }
        }
        return false
    }
}

// https://st-lab.tistory.com/267 ( 위의 문제 시리즈 문제, 링크 다 풀고 한 번 참고 )
// Silver2 - 3 hour (Binary Search)
class Problem10816 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val nList = mutableListOf<Int>()
        for(e in str1) {
            nList.add(e.toInt())
        }

        nList.sort()
        val scr = Scanner(System.`in`)
        val M: Int = scr.nextInt()

        val sb = StringBuilder()

        for (i in 0 until M) {
            val key: Int = scr.nextInt()
            sb.append(upperBoundBS(nList, key) - lowerBoundBS(nList, key)).append(' ')
        }
        println(sb)
    }

    private fun lowerBoundBS(nList: List<Int>, element: Int): Int {
        var st = 0
        var en = nList.size
        while(st < en) {
            val mid =  st + ((en - st) / 2)
            if(nList[mid] < element) {
                st = mid + 1
            }
            else if(nList[mid] >= element) {
                en = mid
            }
        }
        return st
    }

    private fun upperBoundBS(nList: List<Int>, element: Int): Int {
        var st = 0
        var en = nList.size
        while(st < en) {
            val mid =  st + ((en - st) / 2)
            if(nList[mid] <= element) {
                st = mid + 1
            }
            else if(nList[mid] > element) {
                en = mid
            }
        }
        return st
    }
}

class Problem22871 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str = readln().split(" ")
        val aList = mutableListOf<Pair<Int, Int>>() // Pair(index, a)
        for(i in str.indices) {
            val a = str[i].toInt()
            aList.add(Pair(i, a))
        }

        var startIndex = 0
        var pairMinK: Pair<Int, Long>
        var maxK = 0L
        while(startIndex < aList.lastIndex) {
            pairMinK = getMinKIndex(aList, startIndex)
            if(pairMinK.second >= maxK)
                maxK = pairMinK.second

            startIndex = pairMinK.first
        }
        println(maxK)
    }

    private fun getMinKIndex(aList: List<Pair<Int, Int>>, startIndex: Int): Pair<Int, Long> {
        var minK = 10000000L
        var minIndex = startIndex
        for(i in startIndex+1 until aList.size) {
            val k: Long = 1L * (aList[i].first - startIndex) * (1 + abs(aList[i].second - aList[startIndex].second))
            if(k < minK) {
                minK = k
                minIndex = i
            }
        }
        return Pair(minIndex, minK)
    }
}

// Silver3 - 30min (Dynamic Programming) 해답 봄
class Problem1463 {
    fun solve() {
        val x = readln().toInt()
        val dpList = mutableListOf<Int>()
        dpList.add(0)
        dpList.add(0)
        for(i in 2..x) {
            dpList.add(dpList[i-1] + 1)
            if(i % 2 == 0)
                dpList[i] = min(dpList[i], dpList[i/2] + 1)
            if(i % 3 == 0)
                dpList[i] = min(dpList[i], dpList[i/3] + 1)
        }
        println(dpList[x])
    }
}

class Problem2579 {
    fun solve() {
        val n = readln().toInt()
        val stairs = mutableListOf<Int>()
        for(i in 0 until n) {
            stairs.add(readln().toInt())
        }

        var scoreSum = 0
        var jumpStair = false
        for(i in 0 until stairs.size - 3) { // -3의 이유는 i+2 때문에 -2, 마지막 계단은 무조건 밟아야하므로 제외햐서 -1 총 -3
            if(jumpStair) {
                jumpStair = false
                continue
            }
            val sum1 = stairs[i] + stairs[i+1]
            val sum2 = stairs[i] + stairs[i+2]
            val max = max(sum1, sum2)
            val sum3 = stairs[i+1] + stairs[i+2]

            scoreSum += stairs[i] // 해당 계단 점수 + 해주기
            if(max < sum3) // 건너뛰기
                jumpStair = true
        }

        if(jumpStair) {
            scoreSum += stairs[stairs.lastIndex-1]
        }
        else {
            scoreSum += max(stairs[stairs.lastIndex], stairs[stairs.lastIndex-1])
        }
        println(scoreSum + stairs.last())
    }
}

// Silver5
class Problem1010 {
    fun solve() {
        val nRepeat = readln().toInt()
        for(i in 0 until nRepeat) {
            val str = readln().split(" ")
            val n = str[0].toLong()
            val m = str[1].toLong()
            println(combination(m, n))
        }
    }

    private fun combination(n: Long, r: Long): Long {
        var ret = 1L
        repeat(r.toInt()) { r ->
            ret *= (n-r)
            ret /= r+1
        }
        return ret
    }
}

// Silver1 - 40min (BFS)
class Problem1926 {
    private lateinit var vis: Array<Array<Int>>
    private lateinit var paper: Array<Array<Int>>
    private var row by Delegates.notNull<Int>()
    private var col by Delegates.notNull<Int>()
    fun solve() {

        // input
        val scr = Scanner(System.`in`)
        row = scr.nextInt()
        col = scr.nextInt()

        paper = Array(row) { Array(col) {0} }
        vis = Array(row) { Array(col) {0} }

        for(i in 0 until row) {
            for(j in 0 until col) {
                paper[i][j] = scr.nextInt()
            }
        }

        val drawingData = mutableListOf<Int>()
        for(i in 0 until row) {
            for(j in 0 until col) {
                if(vis[i][j] == 1) continue // 방문한 건 패스
                if(paper[i][j] == 0) continue // 그림이 그려지지 않은 곳이라면 패스
                val drawingArea = getAreaUsingBfs(i, j)
                drawingData.add(drawingArea)
            }
        }

        if(drawingData.isEmpty()) {
            println(0)
            println(0)
        }
        else {
            println(drawingData.size)
            println(drawingData.max())
        }
    }

    private fun getAreaUsingBfs(currentRow: Int, currentCol: Int): Int {
        val dx = arrayOf(1, -1, 0, 0)
        val dy = arrayOf(0, 0, -1, 1)

        val queue = LinkedList<Pair<Int, Int>>()
        var cnt = 1
        vis[currentRow][currentCol] = 1
        queue.add(Pair(currentRow, currentCol))

        while(!queue.isEmpty()) {
            val current = queue.pop()
            for(i in 0 until 4) {
                val nx = current.first + dx[i]
                val ny = current.second + dy[i]
                if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue // paper의 범위를 넘지 않도록 조절
                if(vis[nx][ny] == 1 || paper[nx][ny] == 0) continue     // 방문했거나 그려지지 않은(0) 경우 넘어가기

                vis[nx][ny] = 1
                queue.push(Pair(nx, ny))
                cnt++ // 그림의 넓이 측정
            }
        }
        return cnt
    }
}


class Problem2178 {

    private lateinit var miro: Array<Array<Int>>
    private lateinit var vis: Array<Array<Int>>

    fun solve() {
        // input
        val str = readln().split(" ")
        val row = str[0].toInt()
        val col = str[1].toInt()

        miro = Array(row) { Array(col) {0} }
        vis = Array(row) { Array(col) {0} }

        for(i in 0 until row) {
            val data = readln()
            for(j in 0 until col) {
                miro[i][j] = data[j].digitToInt()
            }
        }

        bfs(row, col)
        println(vis[row-1][col-1])
    }

    private fun bfs(row: Int, col: Int) {
        val queue = LinkedList<Pair<Int, Int>>()
        val dx = listOf(1, 0, 0, -1)
        val dy = listOf(0, 1, -1, 0) // 동 남 북 서

        vis[0][0] = 1
        queue.add(Pair(0, 0))

        while(!queue.isEmpty()) {
            val current = queue.pop()
            for(i in 0 until 4) {
                val nx = dx[i] + current.first
                val ny = dy[i] + current.second

                if(nx == row-1 && ny == col-1) { // 도착지점인데, 방문한 이력이 있을 경우
                    if(vis[nx][ny] >= 1)
                        vis[nx][ny] = min(vis[nx][ny], vis[current.first][current.second]+1) // 도착지점 값을 최소값으로 변경
                    else {
                        vis[nx][ny] = vis[current.first][current.second] + 1 // 지금까지 이동해온 거리 + 1
                    }
                }
                if(nx >= row || nx < 0 || ny >= col || ny < 0) continue
                if(miro[nx][ny] == 0 || vis[nx][ny] >= 1) continue
                vis[nx][ny] = vis[current.first][current.second] + 1 // 지금까지 이동해온 거리 + 1
                queue.push(Pair(nx, ny))
            }
        }
    }
}

// Silver2 - 25min (BS)
class Problem2805 {
    fun solve() {
        val str = readln().split(" ")
        val n = str[0].toInt()
        val m = str[1].toInt()

        val scr = Scanner(System.`in`)
        val woodLengths = mutableListOf<Int>()
        repeat(n) {
            woodLengths.add(scr.nextInt())
        }
        woodLengths.sort()
        val maxLength = upperBoundBS(woodLengths, m)
        print(maxLength)

    }
    private fun upperBoundBS(woodLengths: List<Int>, m: Int): Int {
        var st = 0
        var en = woodLengths.last() + 1
        while(st < en) {
            val mid = (st + en) / 2
            val extraLength = getExtraWoodLength(woodLengths, mid)
            if(extraLength >= m) { // length 늘려야
                st = mid + 1
            }
            else {
                en = mid
            }
        }

        return st - 1 // upper이기에 -1
    }

    private fun getExtraWoodLength(woodLengths: List<Int>, mid: Int): Long {
        var sum = 0L
        for(wood in woodLengths) {
            if(wood - mid < 0) continue // 음수는 계산 x
            sum += (wood - mid)
        }
        return sum
    }
}





