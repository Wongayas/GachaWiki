package org.example.learning_spring.iointeractionclasses;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWriter {
    public static void writeData(String filePath){
        File path=new File(filePath);
        if(!path.exists()){
            path.mkdir();
        }
        try{
            FileWriter fw=new FileWriter(filePath);
            CSVWriter csvWriter=new CSVWriter(fw);
            List<String[]> data=new ArrayList<String[]>();
            data.add(new String[]{"name","loreSummary"});
            data.add(new String[]{"Aalto","Aalto is a playable Aero Congenital Resonator in Wuthering Waves. He is an information broker from the New Federation and a Consultant of the Black Shores. He first appears in the Main Quest Chapter I: Act V - Rewinding Raindrops. "});
            data.add(new String[]{"Baizhi","Baizhi (Chinese: 白芷) is a playable Glacio Mutant Resonator in Wuthering Waves. She is a researcher in Remnant Ecoacoustics at Huaxu Academy. Baizhi's once unfulfilled wish has now manifested as her loyal companion. The Remnant Creature You'tan is her source of healing powers, and a lifelong research focus. "});
            data.add(new String[]{"Brant","Brant (Chinese: 布兰特) is a playable Fusion Natural Resonator in Wuthering Waves. He is the captain of the Troupe of Fools, exuding a carefree, easygoing charm and charisma, unbound by convention. However, beneath his flamboyant persona lies a kind heart deeply devoted to ensuring his crew's safety and freedom. He views the world as one of unlimited possibilities and encourages this same optimism in the public through his spectacular performances."});
            data.add(new String[]{"Calcharo","Calcharo is a playable Electro Natural Resonator in Wuthering Waves. He is a former Exile from the New Federation's Lawless Zone and the leader of the Ghost Hounds. "});
            data.add(new String[]{"Camellya","Camellya (Chinese: 椿) is a playable Havoc Artificial Resonator in Wuthering Waves. She is a Bloom Bearer of the Black Shores, and handles the combat-oriented germination tests given to new candidates. However, she refuses all sense of responsibility, delighting in satiating her unquenchable thirst for battle and personal amusement. In pursuit of the thrill of a fight, she gleefully abandons the risks to her life and the people around her."});
            data.add(new String[]{"Cantarella","Cantarella (Chinese: 坎特蕾拉) is a playable Havoc Unknown Resonator in Wuthering Waves. She is the thirty-sixth matriarch of the Fisalia family, and the former Blessed Maiden of Imperator. Her elegant, composed demeanor and captivating beauty conceal a dark and disturbing past, of which she is steadfastly searching for the means to liberate herself and her lineage."});
            data.add(new String[]{"Carlotta","Carlotta (Chinese: 珂莱塔) is a playable Glacio Mutant Resonator in Wuthering Waves. She is the second daughter of the esteemed Montelli Family of Rinascita, embodying innate nobility and a refined appreciation for art. She wields unparalleled intelligence, confidence, cunning, and an ambition to elevate Ragunna City to new heights. Her determination extends to her work of maintaining her family's interests, as well as eliminating those standing in the way of them. "});
            data.add(new String[]{"Cartethyia","Cartethyia (Chinese: 卡提希娅) is a playable Aero Congenital Resonator in Wuthering Waves. Throughout Rinascita, she is revered as Fleurdelys, the \"Blessed Maiden,\" and in some accounts, \"The Martyred Maiden.\" As a key figure in the region’s history, she holds profound influence, with her connections to the Sentinel Imperator, the Threnodian Leviathan, and the Dark Tide. Her true desires, however, lie in freeing herself from the shackles of her titles and living out her days as a just and righteous wandering knight. "});
            data.add(new String[]{"Changli","Changli (Chinese: 长离) is a playable Fusion Natural Resonator in Wuthering Waves. She is the Former Secretary-General of the Central Secretariat in Mingting, the capital, and now serves as Counselor to Jinzhou Magistrate, Jinhsi. Having flourished from an unforgiving childhood, she now stands as a calculated, knowledgeable, and mysterious official and strategist with a keen eye for both political and civilian affairs. "});
            data.add(new String[]{"Chixia","Chixia (Chinese: 炽霞) is a playable Fusion Mutant Resonator in Wuthering Waves. She is a young Patroller who casts her jubilant figure along the streets of Jinzhou city. "});
            data.add(new String[]{"Ciaccona","Ciaccona (Chinese: 夏空) is a playable Aero Resonator in Wuthering Waves. She is a free-spirited, romantic, sincere, and whimsical bard wandering Rinascita in search of inspiration. She is a passionate musician, poet, and storyteller for both commoners as well as the Divinity, and aims to spread her talents, truths, and discoveries of the world around her to all those daring enough to listen. "});
            data.add(new String[]{"Danjin","Danjin (Chinese: 丹瑾) is a playable Havoc Mutant Resonator in Wuthering Waves.She is a member of the Midnight Rangers. "});
            data.add(new String[]{"Encore","Encore (Chinese: 安可) is a playable Fusion Congenital Resonator in Wuthering Waves. She is from the New Federation and a Consultant of the Black Shores. With her vivid imagination, her Wooly companions, Cosmos and Cloudy, and a penchant for crafting fairy tales, she is responsible for finding new clients for the Black Shores alongside her fellow Consultant Aalto."});
            data.add(new String[]{"Jianxin","Jianxin (Chinese: 鉴心) is a playable Aero Natural Resonator in Wuthering Waves. She is a Taoist monk and successor of Fengyiquan (风仪拳). She has dedicated her life to mastering the ultimate martial art. "});
            data.add(new String[]{"Jinhsi","Jinhsi (Chinese: 今汐) is a playable Spectro Congenital Resonator in Wuthering Waves. She is the young Magistrate of Jinzhou with a resolute and altruistic nature and an unbreakable dedication to defending her city and countrymen to her last breath. She is also the appointed Resonator of Sentinel Jué, manifesting in the shape of a Loong, since birth, and with it the responsibility to safeguard Jinzhou's past and guide its future. "});
            data.add(new String[]{"Jiyan","Jiyan (Chinese: 忌炎) is a playable Aero Mutant Resonator in Wuthering Waves. He is the general of the Midnight Rangers selected by the sentinel of Jinzhou, Jué and is stationed in the same city. "});
            data.add(new String[]{"Lingyang","Lingyang (Chinese: 凌阳) is a playable Glacio Natural Resonator in Wuthering Waves. He is a member of the Liondance Troupe in Jinzhou and the last living Suan'ni. "});
            data.add(new String[]{"Lumi","Lumi (Chinese: 灯灯 Dēngdēng) is a playable Electro Congenital Resonator in Wuthering Waves. She is a navigator at Lollo Logistics and leader of a transport squad team. "});
            data.add(new String[]{"Lupa","Lupa (Chinese: 露帕) is a playable Fusion Non-congenital Resonator in Wuthering Waves. She is a gladiator from Septimont, a warrior and undefeated champion of the arena with both an untamed, competitive spirit and an honorable, valiant heart. While indulging in the adrenaline of hard-fought victories, she is also a loyal, prideful protector of her city and upholds its values with everything she holds dear. "});
            data.add(new String[]{"Mortefi","Mortefi is a playable Fusion Mutant Resonator in Wuthering Waves. He hails from the New Federation and is the head of the Branch of Tacetite Weaponry within the Department of Safety in Huaxu Academy at Jinzhou. "});
            data.add(new String[]{"Phoebe","Phoebe (Chinese: 菲比) is a playable Spectro Resonator in Wuthering Waves. She is an Acolyte of the Order of the Deep. A young woman of quiet devotion and compassionate, nurturing nature, she upholds the teachings of her faith with unwavering diligence. Raised her entire life as a devout Acolyte, she extends her heartfelt kindness to everyone she meets with her good graces and unquestionable reverence to Sentinel Imperator. "});
            data.add(new String[]{"Phrolova","Phrolova (Chinese: 弗洛洛) is a playable Havoc Mutant Resonator in Wuthering Waves. She is one of the Overseers of the Fractsidus, and is a particularly powerful Resonator who can manipulate and transfigure the frequencies of humans, Echoes, and Tacet Discords alike. A life filled with unexpected suffering, grief, and betrayal led this former musician to align with the Fractsidus, in which she seeks to rekindle a past from which she was the sole survivor. After completing Main Quest Dreamcatchers in the Secret Gardens in Chapter II: Act VII, she can be found in the Lost Beyond. "});
            data.add(new String[]{"Roccia","Roccia (Chinese: 洛可可) is a playable Havoc Acquired Resonator in Wuthering Waves. She is a gifted improvisational comedian and the First Mate of the Troupe of Fools. While bashful and introverted, she, alongside Pero, her closest companion, possesses a repertoire of tools and talents to protect and nurture her friends and newfound family. "});
            data.add(new String[]{"Rover (Aero)","Rover is a multi-attribute Resonator and is the main protagonist of Wuthering Waves. Awakened with an unknown past, Rover is an Arbiter who embarks on a journey to uncover the truth to regain their lost memories. As secrets are unveiled, they establish deeper connections with the world. "});
            data.add(new String[]{"Rover (Havoc)","Rover is a multi-attribute Resonator and is the main protagonist of Wuthering Waves. Awakened with an unknown past, Rover is an Arbiter who embarks on a journey to uncover the truth to regain their lost memories. As secrets are unveiled, they establish deeper connections with the world. "});
            data.add(new String[]{"Rover (Spectro)","Rover is a multi-attribute Resonator and is the main protagonist of Wuthering Waves. Awakened with an unknown past, Rover is an Arbiter who embarks on a journey to uncover the truth to regain their lost memories. As secrets are unveiled, they establish deeper connections with the world. "});
            data.add(new String[]{"Sanhua", "Sanhua (Chinese: 散华) is a playable Glacio Mutant Resonator in Wuthering Waves. She is the loyal and reliable guard of Jinzhou Magistrate Jinhsi who perceives a world distinctly different from that in the eyes of others. "});
            data.add(new String[]{"The Shorekeeper", "The Shorekeeper (Chinese: 守岸人) is a playable Spectro Resonator in Wuthering Waves. She is known as \"the Second Instance,\" the Guardian of the Black Shores, and a mythical entity composed of Sonoro Spheres and high-purity Remnant Energy crystals with the power to control the stars, only appearring during times of crisis to aid the true leader of the Black Shores. Even among the organization's guests and members, she remains an incredibly enigmatic figure. "});
            data.add(new String[]{"Taoqi", "Taoqi (Chinese: 桃祈) is a playable Havoc Natural Resonator in Wuthering Waves. She is the director of border defense at the Ministry of Development. "});
            data.add(new String[]{"Verina", "Verina is a playable Spectro Congenital Resonator in Wuthering Waves. She is a botanist from the New Federation who dwells in Jinzhou, Huanglong. "});
            data.add(new String[]{"Xiangli Yao", "Xiangli Yao (Chinese: 相里要) is a playable Electro Mutant Resonator in Wuthering Waves. He is the Principal Investigator in charge of the academics at Huaxu Academy's Jinzhou Campus. He is the Academy's youngest multi-disciplinary scientist, and an expert in the research of Automata Mechanics. "});
            data.add(new String[]{"Yangyang", "Yangyang (Chinese: 秧秧) is a playable Aero Natural Resonator in Wuthering Waves. She is an outrider of the Midnight Rangers. "});
            data.add(new String[]{"Yinlin", "Yinlin (Chinese: 吟霖) is a playable Electro Congenital Resonator in Wuthering Waves. She was previously an outstanding Jinzhou Patroller, known for being steady and reliable. "});
            data.add(new String[]{"Youhu", "Youhu (Chinese: 釉瑚) is a playable Glacio Mutant Resonator in Wuthering Waves. She is an antiquarian from Huanglong. "});
            data.add(new String[]{"Yuanwu", "Yuanwu (Chinese: 渊武) is a playable Electro Natural Resonator in Wuthering Waves. He owns a boxing gym where he teaches martial arts and health management, and is well-respected in the community for his cordial demeanor and pleasant temperament. "});
            data.add(new String[]{"Zani", "Zani (Chinese: 赞妮) is a playable Spectro Mutant Resonator in Wuthering Waves. She is a staff member of the Montelli Family's Averardo Bank in Ragunna. As a serious and reliable Montelli employee, Zani follows a strict routine and manages tasks with ease. For years, she has clocked in on time without fail, finding as much enjoyment in her well-ordered life as in her carefully planned moments of leisure. "});
            data.add(new String[]{"Zhezhi", "Zhezhi (Chinese: 折枝) is a playable Glacio Congenital Resonator in Wuthering Waves. She is a commissioned painter, quiet and shy with a dedication to her craft. She's not very eloquent, but her persistence and love for painting speak volumes. "});
            csvWriter.writeAll(data);

            csvWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeData("src/main/resources/CSV/CharLore.csv");
    }
}
