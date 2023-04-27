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