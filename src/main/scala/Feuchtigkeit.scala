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
      }
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

      val fsc = FScapeJobs()
      fsc.verbose = true

      val pathEnergy = WORK_PATH + fs + "WaterContainer2MSKEnergy.aif"
      val afEnergy   = AudioFile.openRead( pathEnergy )
      val bufEnergy  = {
         val b = afEnergy.frameBuffer( afEnergy.numFrames.toInt )
         afEnergy.readFrames( b )
         b( 0 )
      }
      afEnergy.close
      val pathProc = WORK_PATH + fs + "WaterContainer2MRsmpExcHP.aif"
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
      val chunksS = chunks.sortBy( _.energy )
      val maxChunkLen = chunks.map( _.length ).max.toInt
      val chunkBuf = afIn.frameBuffer( maxChunkLen )

      // maps input time 0 ... 1 to chunk duration
      def timeFun( i: Double ) : Double = {
//         linlin( i, 0, 1, 5, 1 )
         if( i <= 0.5 ) linlin( i, 0, 0.5, 4, 5 ) else linlin( i, 0.5, 1, 5, 1 ) 
      }

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

         val outPath = WORK_PATH + fs + "WaterContainerGesture" + (idx+1) + ".aif"
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