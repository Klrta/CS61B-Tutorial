public class Palindrome {
    public Deque<Character> wordToDeque(String words){
        Deque<Character> deque = new arrayDeque<>();
        for (int i = 0; i < words.length(); i++) {
            char c = words.charAt(i);
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        //ArrayDeque的实现
        Deque<Character> deque = wordToDeque(word);

        while (deque.getStart() < deque.getEnd()){
            if(deque.size() == 0 || deque.size() == 1){
                return true;
            }
            if(deque.removeFirst() != deque.removeLast()){
                return false;
            }
        }
        return true;


//        //字符串的实现
//        //when word.length == 0 or ==1 return true
//        if(word.length() == 0 || word.length() == 1){
//            return true;
//        }
//
//        for(int start = 0, end = word.length() - 1; start < end; start++,end--){
//            if(word.charAt(start) != word.charAt(end)){
//                return false;
//            }
//        }
//        return true;

    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        while (deque.getStart() < deque.getEnd()){
            if(deque.size() == 0 || deque.size() == 1){
                return true;
            }
            if(!cc.equalChars(deque.removeFirst(),deque.removeLast())){
                return false;
            }
        }
        return true;
    }


}
