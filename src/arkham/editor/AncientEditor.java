package arkham.editor;

import arkham.json.*;
import arkham.mechanics.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class AncientEditor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Имя: ");
        String name = sc.nextLine();

        System.out.print("Трек безысходности: ");
        int awakening = Integer.parseInt(sc.nextLine());

        System.out.print("Воздействие (None, Spawn, Investigator_related, Monster_related, Gate_related, Map_related, Environment_related): ");
        String aoType = sc.nextLine();
        String[] aoTypes = aoType.split(", ");
        Set<AncientTypes> aoSet = new HashSet<>();
        for(String x: aoTypes) {
            aoSet.add(AncientTypes.valueOf(x.toUpperCase()));
        }

        System.out.print("Последователи: ");
        String followers = sc.nextLine();

        System.out.print("Название особенности: ");
        String featureName = sc.nextLine();

        System.out.print("Описание особенности: ");
        String featureDesc = sc.nextLine();

        AncientOne ao = new AncientOne(
                name,
                awakening,
                aoSet,
                followers,
                featureName,
                featureDesc
        );

        System.out.println(ao.toString());
    }
}
