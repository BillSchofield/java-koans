package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

@SuppressWarnings("unused")
public class AboutCasting {

	@Koan
	public void longPlusInt() {
		int a = 6;
		long b = 10;
		Object c = a + b;
		assertEquals(c, __);
		assertEquals(c instanceof Integer, __);
		assertEquals(c instanceof Long, __);
	}

	@Koan
	public void forceIntTypecast() {
		long a = 2147483648L;
		// What happens if we force a long value into an int?
		int b = (int) a;
		assertEquals(b, __);
	}

	@Koan
	public void implicitTypecast() {
		int a = 1;
		int b = Integer.MAX_VALUE;
		long c = a + b; // still overflows int... which is the Integer.MIN_VALUE, the operation occurs prior to assignment to long
		assertEquals(c, __);
	}

	interface Speaker {
		String speak();
	}

	class Cat implements Speaker {
		public String speak() {
			return "Meow";
		}
	}

	class Lion extends Cat {
		public String speak() {
			return "Roar";
		}
	}

	class LionCub extends Lion {
		public String speak() {
			return "Grrr";
		}
	}

	@Koan
	public void downCastWithInheritance() {
		LionCub lionCub = new LionCub();
		Lion lionReference = lionCub; // Why isn't there an explicit cast?
		assertEquals(lionCub instanceof LionCub, __);
		assertEquals(lionReference instanceof LionCub, __);
		assertEquals(lionReference instanceof Lion, __);
		assertEquals(lionReference instanceof Cat, __);
	}

	@Koan
	public void downCastAndPolymorphism() {
		LionCub lionCub = new LionCub();
		Lion lionReference = lionCub;
		// If the result is unexpected, consider the difference between an instance and its reference
		assertEquals(lionReference.speak(), __);
	}

	@Koan
	public void upCastWithInheritance() {
		Cat child = new LionCub();
		Lion lionReference = (Lion) child; // Why do we need an explicit cast here?
		LionCub lionCubReference = (LionCub) lionReference; // Or here?
		assertEquals(lionCubReference instanceof LionCub, __);
		assertEquals(lionCubReference instanceof Lion, __);
		assertEquals(lionCubReference instanceof Cat, __);
	}

	@Koan
	public void upCastAndPolymorphism() {
		Cat child = new LionCub();
		Lion lion = (LionCub) child;
		// Think about the result. Did you expect that? Why?
		// How is that different from above?
		assertEquals(lion.speak(), __);
	}

	@Koan
	public void classCasting() {
		try {
			Object o = new Object();
			((Speaker) o).speak(); // would this even compile without the cast?
		} catch (ClassCastException x) {
			fail("Object does not implement Speaker, maybe one of the people classes do?");
		}
	}

	@Koan
	public void complicatedCast() {
		Cat parent = new Lion();
		// How can we access the parent's ability to "speak" - if the reference is held as a superclass?
		assertEquals("TPS reports don't even have a cover letter!", __);
	}

}
