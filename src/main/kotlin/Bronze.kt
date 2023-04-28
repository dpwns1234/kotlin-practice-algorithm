import java.util.*

// Bronze 1
class Problem1546 {
    fun solve() {
        val scr = Scanner(System.`in`)
        // 1. 크기와 배열 생성
        val size = scr.nextInt()
        val arrays: MutableList<Double> = mutableListOf()

        for (i in 0 until size) {
            val input = scr.nextDouble()
            arrays.add(input)
        }

        // 2. 최대값 찾기
        val max = arrays.max()

        // 3. 최대값을 이용해 배열의 값 바꾸기
        for(index in arrays.indices) {
            arrays[index] = arrays[index] / max * 100
        }
        // 4. 평균구하기
        val mean: Double = arrays.sum() / size

        print(mean)
        print('\n')
    }
}

// Bronze 1
class Problem14467 { // 13분
    fun solve() {
        // input
        var moveCount = 0
        val size = readln().toInt()
        val record = mutableMapOf<Int, Int>()
        for(i in 0 until size) { // 이거 있을텐데 그 코틀린 보며 수정
            val input = readln()
            val cow = input.split(" ")[0].toInt()
            val moveDirect = input.split(" ")[1].toInt()
            // cow 없으면 추가
            if(!record.containsKey(cow)) {
                record[cow] = moveDirect
            }
            else {
                val preciousDirect = record[cow]
                // 이전에 있었던 방향과 이동한 방향이 다르다면 길을 건넌 것이므로 cnt+1
                if(preciousDirect != moveDirect)
                    moveCount++
            }
        }
        print(moveCount)
    }
}