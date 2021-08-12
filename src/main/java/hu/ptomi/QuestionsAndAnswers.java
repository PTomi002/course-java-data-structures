package hu.ptomi;

/**
 * What is O(1)?
 * + constant time
 *
 * What is O(log n)?
 * + logarithmic time
 *
 * What is O(k^n), thus some constant k to the power of n?
 * + exponential time
 *
 * What is O(n * log n)?
 * + quasilinear time
 *
 * For a quadratic function, when n=10_000_000 it takes 2 seconds to complete. How long will it take approximately when n=100_000_000?
 * + f(n) is quadratic
 * + f(10_000_000) = 2 sec
 * + f(100_000_000) = 200 sec
 * +    linear case: 2 * 10 sec, because the input is 10 times more, but the method is linear
 * +    quadratic case: 2 * 10 * 10 sec, because the input is 10 times more, but the method is quadratic
 * + Let's say it takes S seconds for N elements and that it's a quadratic algorithm.
 * +    You now want to figure out how long it will take for M elements.
 * +    The answer is approximately S * (M/N) * (M/N).
 * +    In our case, S = 2.  N = 10m.  M = 100m.  Thus M/N = 10.  Therefore the correct answer is 2 * 10 * 10.
 *
 * In an English dictionary with ten million words, what is the maximum number of steps needed to find any single word?
 * + 24 (not sqrt(10_000_000))
 *
 * What is faster? O(n) or O(n*logn)
 * + Depends on the size of n.
 *
 * As n approaches infinity, which will be slowest?
 * + O(n*logn)
 */
public interface QuestionsAndAnswers {
}
