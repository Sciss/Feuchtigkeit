/*
 *  Feuchtigkeit.scala
 *  (Feuchtigkeit)
 *
 *  Copyright (c) 2010 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either
 *  version 2, june 1991 of the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License (gpl.txt) along with this software; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 *
 *
 *  Changelog:
 */

import de.sciss.fscape.FScapeJobs
import de.sciss.synth.io._
import FScapeJobs._
import java.io.File
import Util._

object Feuchtigkeit {
   val fs         = File.separator
   val BASE_PATH  = "/Users/hhrutz/Desktop/Feuchtigkeit"
   val AUDIO_PATH = BASE_PATH + fs + "audio_work"
   val WORK_PATH  = AUDIO_PATH + fs + "work"

   type Channel = Array[ Float ]
   val rnd = new util.Random()

   def main( args: Array[ String ]) {
      args.toSeq match {
         case Seq( "--waterContainer2" ) => waterContainer2
         case Seq( "--waterContainer3" ) => waterContainer3 
         case Seq( "--waterContainer6" ) => waterContainer6 
         case Seq( "--saatchiReverse" )  => saatchiReverse 
      }
   }

   def saatchiReverse {
      val lengths = Vector(
                0,   592835,   808712,  1026136,  1243453,  1667869,  1890942,  2107200,  2322311,  2545384,  2761498,
          2978964,  3194841,  3411657,  3628499,  3844885,  4060762,  4278079,  4493226,  4709104,  4927859,  5145380,
          5360420,  5577508,  5790500,  6003203,  6224387,  6441475,  6658217,  6875305,  7090778,  7307866,  7524955,
          7742043,  7957533,  8174621,  8391709,  8608797,  8824569,  9041657,  9258745,  9475833,  9693397,  9908437,
         10127573, 10342613, 10558886, 10775974, 10991014, 11210150, 11423142, 11642278, 11859366, 12076454, 12293359,
         12511091, 12728175, 12943877, 13162206, 13377936, 13595227, 13811519, 14028603, 14245688, 14461617, 14678702,
         14894803, 15113270, 15328972, 15544674, 15763141, 15980071, 16197156, 16414241, 16632808, 16847127, 17062829,
         17280746, 17499214, 17714916, 17932524, 18149609, 18365311, 18581480, 18798565, 19016482, 19232184, 19448448,
         19665533, 19883122, 20099119, 20316540, 20532929, 20750014, 20966196, 21183594, 21401895, 21617109, 21838491,
         22050044, 22270209, 22485911, 22919020, 23138447, 23354794, 23567989, 23786262, 24003503, 24221971, 25040179
      ).sliding( 2, 1 ).map( v => v(1) - v(0) ).toIndexedSeq

      val afIn = AudioFile.openRead( WORK_PATH + fs + "SaatchiInstallation-R.aif" )
      val buf  = afIn.frameBuffer( lengths.max )
      lengths.zipWithIndex foreach { tup =>
         val (chunkLen, idx) = tup
         println( idx )
         val afOut = AudioFile.openWrite( WORK_PATH + fs + "SaatchiInstRCut" + (idx + 1) + ".aif",
            AudioFileSpec( sampleRate = afIn.sampleRate, numChannels = 1 ))
         afIn.readFrames( buf, 0, chunkLen )
         afOut.writeFrames( buf, 0, chunkLen )
         afOut.close
      }
      afIn.close
      println( "Done." )
   }

   def waterContainer2 {
      val spans = Vector(
           (361061 ->   374251),   (374251 ->   386610),   (386610 ->   414474),   (414474 ->   423620),   (423620 ->   436934),
           (436934 ->   467536),   (699038 ->   772490),   (772490 ->   845641),   (845641 ->   873801),   (873801 ->   916553),
           (916553 ->   953687),  (1006647 ->  1023031),  (1023031 ->  1064743),  (1088551 ->  1111591),  (1111591 ->  1140263),
          (1140263 ->  1157799),/*(1157799 ->  1232935),*/(1252007 ->  1274279),  (1274279 ->  1294887),  (1294887 ->  1334951),
          (1368983 ->  1383063),  (1383063 ->  1412247),  (1412247 ->  1467511),  (1467511 ->  1504279),  (1539710 ->  1572606),
          (1572606 ->  1614333),  (1614333 ->  1644413),  (1644413 ->  1664013),  (1664013 ->  1691021),  (1691021 ->  1729037),
          (1729037 ->  1768892),  (1768892 ->  1800483),  (1800483 ->  1838243),  (1838243 ->  1878980),  (1895229 ->  1961917),
          (1961917 ->  2009531),  (2009531 ->  2044379),  (2044379 ->  2078811),  (2078811 ->  2128731),  (2128731 ->  2155083),
          (2155083 ->  2212683),  (2212683 ->  2240842),  (2240842 ->  2272971),  (2272971 ->  2318539),  (2318539 ->  2364635),
          (2364635 ->  2393435),  (2393435 ->  2420228),  (2420228 ->  2449540),  (2449540 ->  2478882),  (2478882 ->  2505634),
          (2505634 ->  2546082),  (2546082 ->  2576290),  (2576290 ->  2610210),  (2610210 ->  2673295),  (2890482 ->  2911858),
          (2911858 ->  2980146),  (2980146 ->  3027522),  (3027522 ->  3105986),  (3105986 ->  3145730),  (3145730 ->  3176834),
          (3176834 ->  3216226),  (3216226 ->  3254626),  (3254626 ->  3286722),  (3286722 ->  3368258),  (3368258 ->  3429890),
          (3429890 ->  3481474),  (3481474 ->  3497649),  (3497649 ->  3515266),  (3515266 ->  3559938),  (3559938 ->  3611973),
          (3611973 ->  3626053),  (3626053 ->  3643461),  (3643461 ->  3658437),  (3658437 ->  3688245),  (3688245 ->  3713973),
          (3713973 ->  3734964),  (3734964 ->  3758516),  (3758516 ->  3803956),  (3803956 ->  3851060),  (3851060 ->  3873844),
          (3873844 ->  3898344),  (3898344 ->  3921090),  (3921090 ->  4019576),  (4059743 ->  4096479),  (4096479 ->  4130143),
          (4130143 ->  4178410),  (4220591 ->  4237086),  (4237086 ->  4272159),  (4272159 ->  4303393),  (4457879 ->  4516439),
          (4516439 ->  4538071),  (4538071 ->  4576659),  (4594971 ->  4617243),  (4617243 ->  4680059),  (4680059 ->  4696443),
          (4696443 ->  4718971),  (4718971 ->  4745723),  (4745723 ->  4828014),  (4850219 ->  4872714),  (4872714 ->  4933638),
          (4980491 ->  5005707),  (5005707 ->  5159078),  (5159078 ->  5187494),  (5187494 ->  5279222),  (5503165 ->  5585037),
          (5585037 ->  5601805),  (5601805 ->  5618189),  (5618189 ->  5659468),  (5659468 ->  5673165),  (5673165 ->  5686989),
          (5686989 ->  5760429),  (5760429 ->  5936068),  (7339715 ->  7400341),  (8746539 ->  8792237),  (8792237 ->  8820760),
          (8820760 ->  8854728),  (8854728 ->  8884818),  (8884818 ->  8953663),  (8953663 ->  9046692),  (9162060 ->  9199267),
          (9199267 ->  9215214),  (9215214 ->  9237644),  (9237644 ->  9265985),  (9265985 ->  9291673),  (9359800 ->  9399344),
          (9399344 ->  9414124),  (9414124 ->  9445111),  (9445111 ->  9473686),  (9473686 ->  9501820),  (9501820 ->  9517119),
          (9517119 ->  9541882),  (9541882 ->  9556403),  (9556403 ->  9642602),  (9642602 ->  9662957),  (9662957 ->  9724499),
          (9839060 ->  9866417),  (9866417 ->  9922192),  (9922192 ->  9953100),  (9953100 ->  9978901),  (9978901 -> 10010160),
         (10010160 -> 10073170), (10073170 -> 10154229), (10154229 -> 10176850), (10176850 -> 10209133), (10209133 -> 10242842),
         (10242842 -> 10274490), (10274490 -> 10304829), (10304829 -> 10365760), (10365760 -> 10389227), (10389227 -> 10447104),
         (10447104 -> 10499613), (10499613 -> 10534291), (10557499 -> 10619582), (10797752 -> 10840408), (10848965 -> 10883595),
         (10883595 -> 10902005), (10902005 -> 10963407), (10963407 -> 10980132), (11006840 -> 11049313), (11049313 -> 11087431),
         (11087431 -> 11138461), (11138461 -> 11202638), (11202638 -> 11242189), (11274425 -> 11309892), (11326804 -> 11363005),
         (11363005 -> 11394720), (11394720 -> 11429466), (11429466 -> 11457600), (11457600 -> 11494162), (11494162 -> 11551316),
         (11551316 -> 11593983), (11691555 -> 11719948) )

      // maps input time 0 ... 1 to chunk duration
      def timeFun( i: Double ) : Double = {
//         linlin( i, 0, 1, 5, 1 )
         if( i <= 0.5 ) linlin( i, 0, 0.5, 4, 5 ) else linlin( i, 0.5, 1, 5, 1 )
      }

      waterContainer( 2, spans, timeFun, _.sortBy( _.energy ))
   }

   def waterContainer3 {
      val spans = Vector(
          (3894743 ->  3949350),  (3968780 ->  4011793),  (4029389 ->  4055485),  (4059904 ->  4116315),  (4123320 ->  4157068),
          (4170328 ->  4217250),  (4217250 ->  4269507),  (4269507 ->  4333474),  (4333474 ->  4393240),  (4393240 ->  4464220),
          (4464220 ->  4537437),  (4537437 ->  4625456),  (4644997 ->  4700813),  (4730921 ->  4791037),  (4800254 ->  4864057),
          (4864057 ->  4939986),  (5007030 ->  5075705),  (5134967 ->  5195137),  (5209210 ->  5284897),  (5318245 ->  5364175),
          (5375604 ->  5423903),  (5423903 ->  5477068),  (5491665 ->  5519490),  (5525758 ->  5593054),  (5593054 ->  5641353),
          (5641353 ->  5710200),  (5710200 ->  5763016),  (5763016 ->  5790652),  (5790652 ->  5816825),  (5816825 ->  5847916),
          (5847916 ->  5898256),  (5909372 ->  5983292),  (5983292 ->  6031107),  (6050647 ->  6089360),  (6089360 ->  6118856),
          (6118856 ->  6166786),  (6166786 ->  6209960),  (6209960 ->  6251254),  (6251254 ->  6292547),  (6292547 ->  6403680),
          (6432630 ->  6493661),  (6493661 ->  6556102),  (6556102 ->  6613987),  (6613987 ->  6678439),  (6697776 ->  6797772),
          (6824450 ->  6868476),  (6868476 ->  6913905),  (6996512 ->  7058467),  (7058467 ->  7196309),  (7196309 ->  7319078),
          (7319078 ->  7408248),  (7448001 ->  7495965),  (7495965 ->  7547963),  (7622675 ->  7704568),  (7704568 ->  7761634),
          (7779061 ->  7858507),  (7872696 ->  7921744),  (7921744 ->  7983261),  (7983261 ->  8035553),  (8035553 ->  8084474),
          (8084474 ->  8134196),  (8134196 ->  8196519),  (8208689 ->  8230816),  (8230816 ->  8254787),  (8254787 ->  8353988),
          (8353988 ->  8408650),  (8408650 ->  8442218),  (8442218 ->  8491230),  (8491230 ->  8546583),  (8546583 ->  8602625),
          (8619221 ->  8649586),  (8649586 ->  8739188),  (8739188 ->  8852863),  (8876742 ->  8946857),  (8946857 ->  9006310),
          (9070617 ->  9153514),  (9352046 ->  9407028),  (9415829 ->  9493216),  (9493216 ->  9605390),  (9605390 ->  9688015),
          (9695020 ->  9730046),  (9730046 ->  9790162),  (9790162 ->  9880961),  (9912838 ->  9969616),  (9978834 -> 10100952),
         (10100952 -> 10128008), (10161859 -> 10228058), (10234325 -> 10300593), (10300593 -> 10342992), (10364007 -> 10418593),
         (10441452 -> 10476846), (10500442 -> 10539523), (10539523 -> 10615958), (10615958 -> 10668681), (10950982 -> 11019926),
         (11019926 -> 11039836), (11039836 -> 11074492), (11074492 -> 11144893), (11144893 -> 11211626), (11244070 -> 11291845),
         (11316916 -> 11377012), (11377012 -> 11422711), (11422711 -> 11499029), (11499029 -> 11548065), (11548065 -> 11616991),
         (11616991 -> 11663814), (11663814 -> 11696996), (11711006 -> 11741239), (11741239 -> 11812913), (11812913 -> 11863716),
         (11863716 -> 11932433), (11932433 -> 12007742), (12007742 -> 12053090), (12053090 -> 12071959), (12071959 -> 12098136),
         (12113025 -> 12179758), (12179758 -> 12247965), (12247965 -> 12305595), (12305595 -> 12352884), (12352884 -> 12377218),
         (12377218 -> 12419248), (12419248 -> 12470496), (12470496 -> 12486350), (12486350 -> 12503309), (12503309 -> 12553820),
         (12553820 -> 12622434), (12622434 -> 12712560), (12712560 -> 12734681), (12734681 -> 12807061), (12852585 -> 12910101),
         (12910101 -> 12949648), (13021057 -> 13049446), (13067143 -> 13106224), (13112861 -> 13160790), (13175907 -> 13268118),
         (13268118 -> 13361765), (13361765 -> 13402571), (13418425 -> 13471884), (13471884 -> 13530287), (13530287 -> 13585854),
         (13600971 -> 13799200), (13804855 -> 13870211), (13870211 -> 13921827), (13932791 -> 13981827), (13981827 -> 14019433),
         (14019433 -> 14045610), (14045610 -> 14079937), (14079937 -> 14091367), (14119387 -> 14171372), (14171372 -> 14240104),
         (14240104 -> 14297988), (14297988 -> 14338544), (14338544 -> 14380866), (14380866 -> 14409255), (14409255 -> 14439856),
         (14439856 -> 14509170), (14509170 -> 14622176), (14692433 -> 14711052), (14733280 -> 14755770), (14755770 -> 14776601),
         (14776601 -> 14820436), (14820436 -> 14851775), (14851775 -> 14908922), (14908922 -> 14930121), (14930121 -> 14982417),
         (14982417 -> 15018733), (15282952 -> 15312447), (15475796 -> 15518288)
      )

      def timeFun( i: Double ) : Double = {
//         linlin( i, 0, 1, 5, 1 )
         if( i <= 0.5 ) linlin( i, 0, 0.5, 1, 5 ) else linlin( i, 0.5, 1, 5, 4 )
      }

      waterContainer( 3, spans, timeFun, _.sortBy( ch => -(ch.energy) ))
   }

   def waterContainer6 {
      val spans = Vector(
            (11300 ->    71695),    (87758 ->   149233),   (197750 ->   240946),   (259093 ->   282500),   (282500 ->   316942),
           (342229 ->   409570),   (457650 ->   486719),   (486719 ->   534030),   (534030 ->   582861),   (582861 ->   614464),
           (684077 ->   787350),   (787350 ->   861692),   (891749 ->  1030317),  (1331637 ->  1365672),  (1365672 ->  1404726),
          (1404726 ->  1460015),  (1460015 ->  1514699),  (1646058 ->  1670272),  (1670272 ->  1693477),  (1693477 ->  1715269),
          (1715269 ->  1744125),  (1744125 ->  1875950),  (1875950 ->  1942487),  (1942487 ->  2007464),  (2015663 ->  2038965),
          (2038965 ->  2073380),  (2099695 ->  2133351),  (2448773 ->  2475404),  (2475404 ->  2502283),  (2545424 ->  2582259),
          (2612360 ->  2688683),  (2688683 ->  2742787),  (2742787 ->  2797001),  (2797001 ->  2868392),  (2868392 ->  2961881),
          (2967898 ->  3010273),  (3022380 ->  3099238),  (3109350 ->  3141279),  (3141279 ->  3205447),  (3205447 ->  3255578),
          (3331129 ->  3418083),  (3439831 ->  3500869),  (3509245 ->  3542428),  (3586992 ->  3667780),  (3706279 ->  3790524),
          (3984363 ->  4037652),  (4037652 ->  4085550),  (4085550 ->  4132670),  (4132670 ->  4198456),  (4198456 ->  4248704),
          (4248704 ->  4301172),  (4301172 ->  4373819),  (4373819 ->  4451108),  (4451108 ->  4551604),  (4551604 ->  4671224),
          (4805163 ->  4868427),  (4868427 ->  5011704),  (5011704 ->  5121079),  (5121079 ->  5187714),  (5453643 ->  5562283),
          (5601359 ->  5774562),  (5990579 ->  6047553),  (6300589 ->  6411754),  (6530437 ->  6613578),  (6613578 ->  6659588),
          (6659588 ->  6715385),  (6715385 ->  6793885),  (6793885 ->  6886760),  (6901658 ->  6948759),  (6948759 ->  7000577),
          (7021292 ->  7080820),  (9872848 ->  9926119),  (9926119 ->  9960177), (10435492 -> 10501936), (10809201 -> 10847944),
         (11119187 -> 11208743), (18120955 -> 18221457), (18423636 -> 18503972), (18745290 -> 18821969), (18934970 -> 19061061),
         (19291277 -> 19330020), (19437794 -> 19499600), (20011840 -> 20056447), (20197840 -> 20268271), (20441598 -> 20484377),
         (21315642 -> 21382690), (21775879 -> 21922004), (22356367 -> 22532844), (22631782 -> 22719485), (23105933 -> 23174541),
         (23372839 -> 23436604), (25431992 -> 25495434)
      )

      def timeFun( i: Double ) : Double = {
         if( i <= 0.5 ) linlin( i, 0, 0.5, 7, 3 ) else linlin( i, 0.5, 1, 3, 7 )
      }

      waterContainer( 6, spans, timeFun, all => {
         val (even, odd) = all.partition( _.idx % 2 == 0 )
         even.sortBy( _.energy ) ++ odd.sortBy( ch => -(ch.energy) )
      })
   }

   def waterContainer( containerIdx: Int, spans: Vector[ (Int, Int) ], timeFun: Double => Double,
      sortFun: Seq[ Chunk ] => Seq[ Chunk ]) {
      
      val fsc = FScapeJobs()
      fsc.verbose = true

      val pathEnergy = WORK_PATH + fs + "WaterContainer" + containerIdx + "MSKEnergy.aif"
      val afEnergy   = AudioFile.openRead( pathEnergy )
      val bufEnergy  = {
         val b = afEnergy.frameBuffer( afEnergy.numFrames.toInt )
         afEnergy.readFrames( b )
         b( 0 )
      }
      afEnergy.close
      val pathProc = WORK_PATH + fs + "WaterContainer" + containerIdx + "MRsmpExcHP.aif"
      val afIn   = AudioFile.openRead( pathProc )
//      val procSpec = afProc.spec // AudioFile.readSpec( pathProc )
      val sr = afIn.sampleRate
      val outSpec = AudioFileSpec( numChannels = 1, sampleRate = sr )
      val scale   = 1.0 / 128 // afEnergy.numFrames.toDouble / procSpec.numFrames
      val rsmp = 4
      val noiseFloor = 0.001f

//      println( "scale = " + scale )

      def fullToSerial( full: Long ) = (full * scale + 0.5).toInt
      def serialToFull( serial: Int ) = (serial / scale + 0.5).toLong

//      val shorten = Set( 2, 6, 11, 17, 24, 25, 28, 38, 41, 83, 134, 167 )
//      val shortenAmt = (0.2 * sr).toInt

      val chunks = spans.zipWithIndex map { tup =>
         val ((start0, stop0), idx) = tup
         val start   = start0 * rsmp
//         val stop    = (if( shorten.contains( idx + 1 )) stop0 - shortenAmt else stop0) * rsmp
         val stop    = stop0 * rsmp
         val off1    = fullToSerial( start )
         val off2    = fullToSerial( stop )
         val energy  = bufEnergy.view( off1, off2 ).sum

         val res = Chunk( idx, start, stop, energy )
//         println( res )
         res
      }

      def findBiggestGap( buf: Channel, off: Int, len: Int, thresh: Float ) : (Int, Int) = {
         var i = off
         val stop = off + len
         var bestLen = 0
         var bestOff = 0
         do {
            while( i < stop && buf( i ) > thresh ) i += 1
            if( i < stop ) {
               var j = i - 1
               while( j >= 0 && buf( j ) <= thresh ) j -= 1
               j += 1
               var k = i + 1
               while( k < stop && buf( k ) <= thresh ) k += 1
               val cnt = k - j
               if( cnt >= bestLen ) {
                  bestLen = cnt
                  bestOff = j
               }
               i = k + 1
            }
         } while( i < stop )
         (bestOff, bestLen)
      }

      val numChunks = chunks.size
      val chunksS = sortFun( chunks )
      val maxChunkLen = chunks.map( _.length ).max.toInt
      val chunkBuf = afIn.frameBuffer( maxChunkLen )

      // prevent VM to exit (FScapeJobs is daemon-actor... we should change that!)
      val dummyThread = (new Thread( new Runnable { def run = Thread.sleep( 999999L )})).start

      def nextChunk( iter: Iterator[ (Chunk, Int) ]) {
         val (chunk, idx) = iter.next
         println( idx )
         val boff = fullToSerial( chunk.start + chunk.length / 2 )
         val blen = fullToSerial( chunk.stop ) - boff
         val (fadeStart0, fadeStop) = findBiggestGap( bufEnergy, boff, blen, noiseFloor ) match {
//            case (_, 0) => (chunk.stop - (0.1 * sr).toLong) -> chunk.stop
            case (_, 0) => (chunk.stop - (1.0 * sr).toLong) -> chunk.stop
            case (o, l) => serialToFull( o ) -> serialToFull( o + l )
         }
//         val fadeStart = math.max( chunk.start, fadeStart0 )
         val fadeStart = math.max( math.max( chunk.start, fadeStart0 ), fadeStop - (3.0 * sr).toInt )

//         val afOut = AudioFile.openWrite( WORK_PATH + fs + "WaterContainerChunk" + (idx+1) + ".aif", outSpec )
         val chunk1Path = WORK_PATH + fs + "chunk1.aif"
         val afOut = AudioFile.openWrite( chunk1Path, outSpec )
         afIn.seekFrame( chunk.start )
         val len = (fadeStop - chunk.start).toInt // chunk.length.toInt
         afIn.readFrames( chunkBuf, 0, len )
         fadeIn( chunkBuf( 0 ), 0, (0.004 * sr).toInt )
         fadeOutEqP( chunkBuf( 0 ), (fadeStart - chunk.start).toInt, (fadeStop - fadeStart).toInt )
         afOut.writeFrames( chunkBuf, 0, len )
         afOut.close

         val convInPath = WORK_PATH + fs + "tail.aif"
         val afOut2 = AudioFile.openWrite( convInPath, outSpec )
         afIn.seekFrame( fadeStart )
         val len2 = (fadeStop - fadeStart).toInt
         afIn.readFrames( chunkBuf, 0, len2 )
//         val fadeTail  = math.min( len2 / 2, (1.0 * sr).toInt )
         val fadeTail  = math.min( len2 / 2, fadeStop - fadeStart ).toInt
         val fadeOver  = math.min( len, (fadeTail * 1.5).toInt )
         fadeInSqr( chunkBuf( 0 ), 0, fadeTail )
         fadeOut( chunkBuf( 0 ), len2 - fadeTail, fadeTail )
         afOut2.writeFrames( chunkBuf, 0, len2 )
         afOut2.close

         val dur = timeFun( idx.toDouble / numChunks )
         val noiseDur = math.max( 1.0, dur - (len - fadeOver) / sr ) 

         val convImpPath = WORK_PATH + fs + "noise.aif"
         val afOut3 = AudioFile.openWrite( convImpPath, outSpec )
         val numNoiseFrames = (noiseDur * sr).toLong // 88200L
         val noiseAmp = 0.008f // (math.sqrt( 1.0 / numNoiseFrames ) * 2).toFloat
         var noiseFramesWritten = 0L
         var lastNoiseSmp = 0f
         while( noiseFramesWritten < numNoiseFrames ) {
            val chunkLen = math.min( maxChunkLen, numNoiseFrames - noiseFramesWritten ).toInt
            lastNoiseSmp = createNoise2( chunkBuf( 0 ), 0, chunkLen, noiseAmp, lastNoiseSmp )
            val w1 = 1.0 - (noiseFramesWritten.toDouble / numNoiseFrames)
            val w2 = (1.0 - ((noiseFramesWritten + chunkLen).toDouble / numNoiseFrames)) - w1
            fade( chunkBuf( 0 ), 0, chunkLen )( f => (w1 + f * w2).toFloat )
            afOut3.writeFrames( chunkBuf, 0, chunkLen )
            noiseFramesWritten += chunkLen
         }
         afOut3.close

         val outPath = WORK_PATH + fs + "WaterContainer" + containerIdx + "Gesture" + (idx+1) + ".aif"
         val chunk2Path = WORK_PATH + fs + "chunk2.aif"
         val convDoc = Convolution( convInPath, convImpPath, chunk2Path )
//         val concDoc = Concat( chunk1Path, chunk2Path, outPath, overlap = "1.5s" )
         val concDoc = Concat( chunk1Path, chunk2Path, outPath, overlap = (fadeOver / sr).toString + "s" )
         fsc.processChain( "conv", convDoc :: concDoc :: Nil )( if( _ ) {
            if( iter.hasNext ) {
               nextChunk( iter )
            } else {
               println( "Done!" )
               System.exit( 0 )
            }
         } else {
            println( "Failure!" )
            System.exit( 1 )
         })
      }

      nextChunk( chunksS.zipWithIndex.iterator )
   }

   def createNoise( buf: Channel, off: Int, len: Int, amp: Float ) {
      var i = off
      val stop = off + len
      val a2 = amp * 2
      while( i < stop ) {
         buf( i ) = (rnd.nextFloat() - 0.5f) * amp
         i += 1
      }
   }

   // slightly darker
   def createNoise2( buf: Channel, off: Int, len: Int, amp: Float, lastSample: Float ) : Float = {
      var i = off
      val stop = off + len
      var out2 = lastSample
      while( i < stop ) {
         val in   = rnd.nextFloat() * 2 - 1
         val out  = 0.2f * in + 0.8f * out2  
         buf( i ) = out * amp
         out2 = out
         i += 1
      }
      out2
   }

   def fadeIn(  buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => f )
   def fadeOut( buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => 1f - f )
   def fadeInEqP(  buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => math.sqrt( f ).toFloat )
   def fadeOutEqP( buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => math.sqrt( 1f - f ).toFloat )
   def fadeInSqr(  buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => f * f )
   def fadeOutSqr( buf: Channel, off: Int, len: Int ) = fade( buf, off, len )( f => { val fn = 1f - f; fn * fn })

   def fade( buf: Channel, off: Int, len: Int )( fun: Float => Float ) {
      var i = 0
      while( i < len ) {
         buf( i + off ) *= fun( i.toFloat / len )
         i += 1
      }
   }

   case class Chunk( idx: Int, start: Long, stop: Long, energy: Double ) {
      def length = stop - start
   }
}