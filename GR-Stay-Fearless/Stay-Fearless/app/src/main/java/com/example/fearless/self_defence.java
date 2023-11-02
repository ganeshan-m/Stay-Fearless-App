package com.example.fearless;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class self_defence extends AppCompatActivity {

    private TextView selfDefenseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_defence);

        selfDefenseTextView = findViewById(R.id.selfDefenseTextView);

        String selfDefenseTips =
                "<p><strong>1. Stay Aware of Your Surroundings:</strong><br>" +
                "Always be attentive to your environment. Avoid distractions like headphones that can prevent you from hearing what is happening around you.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=KVpxP3ZZtAc&pp=ygUhc2VsZiBkZWZlbmNlIHRlY2huaXF1ZXMgZm9yIGdpcmxz\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>2. Trust Your Instincts:</strong><br>" +
                "If something does not feel right, trust your gut feeling. If you feel uneasy, it's okay to remove yourself from the situation.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=zTt-IGK-dm8&pp=ygUUdHJ1c3QgeW91ciBpbnN0aW5jdHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>3. Carry Personal Safety Tools:</strong><br>" +
                "Items like pepper spray, a personal alarm, or a whistle can help deter attackers and draw attention if you are in danger.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=YwdZWDbfU4E&pp=ygUyc2VsZiBkZWZlbmNlIENhcnJ5IFBlcnNvbmFsIFNhZmV0eSBUb29scyBmb3IgZ2lybHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>4. Learn Basic Techniques:</strong><br>" +
                "Taking self-defence classes can teach you basic techniques to protect yourself and build your confidence.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=9m-x64bKfR4&pp=ygUsc2VsZiBkZWZlbmNlIGxlYXJuIGJhc2ljIHRlY2huaXF1ZSBmb3IgZ2lybHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>5. Use Your Voice:</strong><br>" +
                "Yell or scream loudly if you feel threatened. This can startle your attacker and attract attention.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=bE0_QZgIXPw&pp=ygUlc2VsZiBkZWZlbmNlIHVzZSB5b3VyIHZvaWNlIGZvciBnaXJscw%3D%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>6. Maintain Personal Space:</strong><br>" +
                "Keep a safe distance from strangers. Stand confidently and be prepared to defend your personal space.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=d87VgAhWnMk&pp=ygUuc2VsZiBkZWZlbmNlIG1haW50YWluIHBlcnNvbmFsIHNwYWNlIGZvciBnaXJscw%3D%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>7. Know Your Weak Points:</strong><br>" +
                "Learn where an attacker's vulnerable points are and use them to your advantage if needed.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=ujdtSGogV9g&pp=ygUsc2VsZiBkZWZlbmNlIGtub3cgeW91ciB3ZWFrIHBvaW50cyBmb3IgZ2lybHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>8. Carry Personal Alarm: </strong><br>" +
                " Carry a personal alarm that you can activate to attract attention and deter potential threats.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=Y1Sv657L2lA&pp=ygUwc2VsZiBkZWZlbmNlIGNhcnJ5IHlvdXIgcGVyc29uYWwgYWxhcm0gZm9yIGdpcmxz\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>9. Use Everyday Objects: </strong><br>" +
                "Everyday items like keys or pens can be used as improvised self-defence tools if necessary.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=Byhlir8rvhQ&pp=ygUrc2VsZiBkZWZlbmNlIFVzZSBFdmVyeWRheSBPYmplY3RzIGZvciBnaXJscw%3D%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>10. Stay in Well-Lit Areas:</strong><br>" +
                "Stick to well-lit and populated areas, especially at night. Avoid shortcuts through isolated places.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=0UqK3tfuu8Q&pp=ygUtc2VsZiBkZWZlbmNlIFN0YXkgaW4gV2VsbC1MaXQgQXJlYXMgZm9yIGdpcmxz\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>11. Share Your Location:</strong><br>" +
                "Let someone you trust know where you're going and when you'll be back, especially if you're going out alone.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=k9Jn0eP-ZVg&pp=ygUqc2VsZiBkZWZlbmNlIHNoYXJlIHlvdXIgbG9jYXRpb24gZm9yIGdpcmxz\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>12. Use Technology:</strong><br>" +
                "Utilize safety apps that can alert your contacts or authorities in case of an emergency.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=XN6E5rc1ghQ&pp=ygUlc2VsZiBkZWZlbmNlIFVzZSBUZWNobm9sb2d5IGZvciBnaXJscw%3D%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>13.Confident Body Language:</strong><br>" +
                "Walk confidently with your head up and shoulders back. This shows that you're aware and assertive.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=r45Oxv2wlDg&pp=ygUvc2VsZiBkZWZlbmNlIENvbmZpZGVudCBCb2R5IExhbmd1YWdlOiBmb3IgZ2lybHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>14. Avoid Routine Patterns:</strong><br>" +
                "Change your routes and routines to avoid predictability.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=d87VgAhWnMk&pp=ygUtc2VsZiBkZWZlbmNlIEF2b2lkIFJvdXRpbmUgUGF0dGVybnMgZm9yIGdpcmxz\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>15. Use Verbal De-escalation:</strong><br>" +
                "Stay calm and use a strong voice to assert your boundaries. Often, talking your way out of a situation can work.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=SqmS9yx_3tc&pp=ygUvc2VsZiBkZWZlbmNlIFVzZSBWZXJiYWwgRGUtZXNjYWxhdGlvbiBmb3IgZ2lybHM%3D\">Click Here</a>"+
                "<br>" +
                "<br>" +
                "<p><strong>16. Trust Your Support System:</strong><br>" +
                "If you feel unsafe, reach out to friends, family, or authorities. Don't hesitate to ask for help.<br>" +
                "<strong>Watch this video:</strong> <a href=\"https://www.youtube.com/watch?v=o2MLjPlW2I0&pp=ygUuc2VsZiBkZWZlbmNlVHJ1c3QgWW91ciBTdXBwb3J0IFN5c3RlbWZvciBnaXJscw%3D%3D\">Click Here</a>";


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            selfDefenseTextView.setText(Html.fromHtml(selfDefenseTips, Html.FROM_HTML_MODE_LEGACY));
        } else {
            selfDefenseTextView.setText(Html.fromHtml(selfDefenseTips));
        }

        // Make the links clickable
        selfDefenseTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
