package problems;

import java.util.ArrayList;

/**
 * 
 * @author Zach Wellman
 * Date Started: 5/1/19
 * Date Completed: 5/1/19
 * Number 13
 *
 */
public class ArrayListBuckets {
	
	public static void main(String[] args) {
		ArrayListBuckets bucket = new ArrayListBuckets();
		ArrayList<String> words = new ArrayList<String>();
		// Input words to insert
		String[] wordList = {"Lugia", "Test", "Azelf", "Buizel", "Bake", "Suicune", "Jolteon", "Eevee", "Duck", 
				"Cresselia", "Something", "Scatter", "Species", "True", "False", "Rebel", "Noice", "Communist",
				"Paint", "Protest", "Rhythm", "Factory", "Whip", "Seperate", "Quilava", "Socialist", "Bike", 
				"Understanding", "Unity", "Kidney", "Master", "Kitsune", "Fox", "Floor", "Bird", "Unruly", 
				"Allow", "Animal", "Wing", "Grandmother", "Yell", "Stand", "Story", "Dive", "Pretend", "Bun"};
		for (int i = 0; i < wordList.length; i++) {
			words.add(wordList[i]);
		}
		ArrayList<ArrayList<String>> buckets = bucket.test(words);
		System.out.println(buckets);
	}

	private ArrayList<ArrayList<String>> test(ArrayList<String> words) {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<String> e = new ArrayList<String>();
		ArrayList<String> f = new ArrayList<String>();
		ArrayList<String> g = new ArrayList<String>();
		ArrayList<String> h = new ArrayList<String>();
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> j = new ArrayList<String>();
		ArrayList<String> k = new ArrayList<String>();
		ArrayList<String> l = new ArrayList<String>();
		ArrayList<String> m = new ArrayList<String>();
		ArrayList<String> n = new ArrayList<String>();
		ArrayList<String> o = new ArrayList<String>();
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<String> q = new ArrayList<String>();
		ArrayList<String> r = new ArrayList<String>();
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> u = new ArrayList<String>();
		ArrayList<String> v = new ArrayList<String>();
		ArrayList<String> w = new ArrayList<String>();
		ArrayList<String> x = new ArrayList<String>();
		ArrayList<String> y = new ArrayList<String>();
		ArrayList<String> z = new ArrayList<String>();
		ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>();
		for (int ia = 0; ia < words.size(); ia++) {
			String check = words.get(ia);
			String firstChar = check.substring(0, 1).toLowerCase();
			switch (firstChar) {
			case "a":
				a.add(check);
				break;
			case "b":
				b.add(check);
				break;
			case "c":
				c.add(check);
				break;
			case "d":
				d.add(check);
				break;
			case "e":
				e.add(check);
				break;
			case "f":
				f.add(check);
				break;
			case "g":
				g.add(check);
				break;
			case "h":
				h.add(check);
				break;
			case "i":
				i.add(check);
				break;
			case "j":
				j.add(check);
				break;
			case "k":
				k.add(check);
				break;
			case "l":
				l.add(check);
				break;
			case "m":
				m.add(check);
				break;
			case "n":
				n.add(check);
				break;
			case "o":
				o.add(check);
				break;
			case "p":
				p.add(check);
				break;
			case "q":
				q.add(check);
				break;
			case "r":
				r.add(check);
				break;
			case "s":
				s.add(check);
				break;
			case "t":
				t.add(check);
				break;
			case "u":
				u.add(check);
				break;
			case "v":
				v.add(check);
				break;
			case "w":
				w.add(check);
				break;
			case "x":
				x.add(check);
				break;
			case "y":
				y.add(check);
				break;
			case "z":
				z.add(check);
				break;
			}
		}
		buckets.add(a);
		buckets.add(b);
		buckets.add(c);
		buckets.add(d);
		buckets.add(e);
		buckets.add(f);
		buckets.add(g);
		buckets.add(h);
		buckets.add(i);
		buckets.add(j);
		buckets.add(k);
		buckets.add(l);
		buckets.add(m);
		buckets.add(n);
		buckets.add(o);
		buckets.add(p);
		buckets.add(q);
		buckets.add(r);
		buckets.add(s);
		buckets.add(t);
		buckets.add(u);
		buckets.add(v);
		buckets.add(w);
		buckets.add(x);
		buckets.add(y);
		buckets.add(z);
		return buckets;
	}
}
